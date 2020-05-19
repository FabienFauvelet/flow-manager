package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FlowManagerApp;
import com.mycompany.myapp.domain.ArchitectureComponent;
import com.mycompany.myapp.repository.ArchitectureComponentRepository;
import com.mycompany.myapp.service.ArchitectureComponentService;
import com.mycompany.myapp.service.dto.ArchitectureComponentDTO;
import com.mycompany.myapp.service.mapper.ArchitectureComponentMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ArchitectureComponentResource} REST controller.
 */
@SpringBootTest(classes = FlowManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArchitectureComponentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ArchitectureComponentRepository architectureComponentRepository;

    @Autowired
    private ArchitectureComponentMapper architectureComponentMapper;

    @Autowired
    private ArchitectureComponentService architectureComponentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArchitectureComponentMockMvc;

    private ArchitectureComponent architectureComponent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArchitectureComponent createEntity(EntityManager em) {
        ArchitectureComponent architectureComponent = new ArchitectureComponent()
            .name(DEFAULT_NAME);
        return architectureComponent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArchitectureComponent createUpdatedEntity(EntityManager em) {
        ArchitectureComponent architectureComponent = new ArchitectureComponent()
            .name(UPDATED_NAME);
        return architectureComponent;
    }

    @BeforeEach
    public void initTest() {
        architectureComponent = createEntity(em);
    }

    @Test
    @Transactional
    public void createArchitectureComponent() throws Exception {
        int databaseSizeBeforeCreate = architectureComponentRepository.findAll().size();
        // Create the ArchitectureComponent
        ArchitectureComponentDTO architectureComponentDTO = architectureComponentMapper.toDto(architectureComponent);
        restArchitectureComponentMockMvc.perform(post("/api/architecture-components")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(architectureComponentDTO)))
            .andExpect(status().isCreated());

        // Validate the ArchitectureComponent in the database
        List<ArchitectureComponent> architectureComponentList = architectureComponentRepository.findAll();
        assertThat(architectureComponentList).hasSize(databaseSizeBeforeCreate + 1);
        ArchitectureComponent testArchitectureComponent = architectureComponentList.get(architectureComponentList.size() - 1);
        assertThat(testArchitectureComponent.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createArchitectureComponentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = architectureComponentRepository.findAll().size();

        // Create the ArchitectureComponent with an existing ID
        architectureComponent.setId(1L);
        ArchitectureComponentDTO architectureComponentDTO = architectureComponentMapper.toDto(architectureComponent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArchitectureComponentMockMvc.perform(post("/api/architecture-components")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(architectureComponentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArchitectureComponent in the database
        List<ArchitectureComponent> architectureComponentList = architectureComponentRepository.findAll();
        assertThat(architectureComponentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArchitectureComponents() throws Exception {
        // Initialize the database
        architectureComponentRepository.saveAndFlush(architectureComponent);

        // Get all the architectureComponentList
        restArchitectureComponentMockMvc.perform(get("/api/architecture-components?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(architectureComponent.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getArchitectureComponent() throws Exception {
        // Initialize the database
        architectureComponentRepository.saveAndFlush(architectureComponent);

        // Get the architectureComponent
        restArchitectureComponentMockMvc.perform(get("/api/architecture-components/{id}", architectureComponent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(architectureComponent.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingArchitectureComponent() throws Exception {
        // Get the architectureComponent
        restArchitectureComponentMockMvc.perform(get("/api/architecture-components/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArchitectureComponent() throws Exception {
        // Initialize the database
        architectureComponentRepository.saveAndFlush(architectureComponent);

        int databaseSizeBeforeUpdate = architectureComponentRepository.findAll().size();

        // Update the architectureComponent
        ArchitectureComponent updatedArchitectureComponent = architectureComponentRepository.findById(architectureComponent.getId()).get();
        // Disconnect from session so that the updates on updatedArchitectureComponent are not directly saved in db
        em.detach(updatedArchitectureComponent);
        updatedArchitectureComponent
            .name(UPDATED_NAME);
        ArchitectureComponentDTO architectureComponentDTO = architectureComponentMapper.toDto(updatedArchitectureComponent);

        restArchitectureComponentMockMvc.perform(put("/api/architecture-components")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(architectureComponentDTO)))
            .andExpect(status().isOk());

        // Validate the ArchitectureComponent in the database
        List<ArchitectureComponent> architectureComponentList = architectureComponentRepository.findAll();
        assertThat(architectureComponentList).hasSize(databaseSizeBeforeUpdate);
        ArchitectureComponent testArchitectureComponent = architectureComponentList.get(architectureComponentList.size() - 1);
        assertThat(testArchitectureComponent.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingArchitectureComponent() throws Exception {
        int databaseSizeBeforeUpdate = architectureComponentRepository.findAll().size();

        // Create the ArchitectureComponent
        ArchitectureComponentDTO architectureComponentDTO = architectureComponentMapper.toDto(architectureComponent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArchitectureComponentMockMvc.perform(put("/api/architecture-components")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(architectureComponentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArchitectureComponent in the database
        List<ArchitectureComponent> architectureComponentList = architectureComponentRepository.findAll();
        assertThat(architectureComponentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArchitectureComponent() throws Exception {
        // Initialize the database
        architectureComponentRepository.saveAndFlush(architectureComponent);

        int databaseSizeBeforeDelete = architectureComponentRepository.findAll().size();

        // Delete the architectureComponent
        restArchitectureComponentMockMvc.perform(delete("/api/architecture-components/{id}", architectureComponent.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ArchitectureComponent> architectureComponentList = architectureComponentRepository.findAll();
        assertThat(architectureComponentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
