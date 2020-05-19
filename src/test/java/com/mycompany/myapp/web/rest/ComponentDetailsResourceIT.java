package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FlowManagerApp;
import com.mycompany.myapp.domain.ComponentDetails;
import com.mycompany.myapp.repository.ComponentDetailsRepository;
import com.mycompany.myapp.service.ComponentDetailsService;
import com.mycompany.myapp.service.dto.ComponentDetailsDTO;
import com.mycompany.myapp.service.mapper.ComponentDetailsMapper;

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
 * Integration tests for the {@link ComponentDetailsResource} REST controller.
 */
@SpringBootTest(classes = FlowManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ComponentDetailsResourceIT {

    private static final String DEFAULT_SERVER = "AAAAAAAAAA";
    private static final String UPDATED_SERVER = "BBBBBBBBBB";

    private static final String DEFAULT_PORT = "AAAAAAAAAA";
    private static final String UPDATED_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private ComponentDetailsRepository componentDetailsRepository;

    @Autowired
    private ComponentDetailsMapper componentDetailsMapper;

    @Autowired
    private ComponentDetailsService componentDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComponentDetailsMockMvc;

    private ComponentDetails componentDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComponentDetails createEntity(EntityManager em) {
        ComponentDetails componentDetails = new ComponentDetails()
            .server(DEFAULT_SERVER)
            .port(DEFAULT_PORT)
            .url(DEFAULT_URL);
        return componentDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComponentDetails createUpdatedEntity(EntityManager em) {
        ComponentDetails componentDetails = new ComponentDetails()
            .server(UPDATED_SERVER)
            .port(UPDATED_PORT)
            .url(UPDATED_URL);
        return componentDetails;
    }

    @BeforeEach
    public void initTest() {
        componentDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createComponentDetails() throws Exception {
        int databaseSizeBeforeCreate = componentDetailsRepository.findAll().size();
        // Create the ComponentDetails
        ComponentDetailsDTO componentDetailsDTO = componentDetailsMapper.toDto(componentDetails);
        restComponentDetailsMockMvc.perform(post("/api/component-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(componentDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the ComponentDetails in the database
        List<ComponentDetails> componentDetailsList = componentDetailsRepository.findAll();
        assertThat(componentDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ComponentDetails testComponentDetails = componentDetailsList.get(componentDetailsList.size() - 1);
        assertThat(testComponentDetails.getServer()).isEqualTo(DEFAULT_SERVER);
        assertThat(testComponentDetails.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testComponentDetails.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createComponentDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = componentDetailsRepository.findAll().size();

        // Create the ComponentDetails with an existing ID
        componentDetails.setId(1L);
        ComponentDetailsDTO componentDetailsDTO = componentDetailsMapper.toDto(componentDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComponentDetailsMockMvc.perform(post("/api/component-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(componentDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComponentDetails in the database
        List<ComponentDetails> componentDetailsList = componentDetailsRepository.findAll();
        assertThat(componentDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllComponentDetails() throws Exception {
        // Initialize the database
        componentDetailsRepository.saveAndFlush(componentDetails);

        // Get all the componentDetailsList
        restComponentDetailsMockMvc.perform(get("/api/component-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(componentDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].server").value(hasItem(DEFAULT_SERVER)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }
    
    @Test
    @Transactional
    public void getComponentDetails() throws Exception {
        // Initialize the database
        componentDetailsRepository.saveAndFlush(componentDetails);

        // Get the componentDetails
        restComponentDetailsMockMvc.perform(get("/api/component-details/{id}", componentDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(componentDetails.getId().intValue()))
            .andExpect(jsonPath("$.server").value(DEFAULT_SERVER))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }
    @Test
    @Transactional
    public void getNonExistingComponentDetails() throws Exception {
        // Get the componentDetails
        restComponentDetailsMockMvc.perform(get("/api/component-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComponentDetails() throws Exception {
        // Initialize the database
        componentDetailsRepository.saveAndFlush(componentDetails);

        int databaseSizeBeforeUpdate = componentDetailsRepository.findAll().size();

        // Update the componentDetails
        ComponentDetails updatedComponentDetails = componentDetailsRepository.findById(componentDetails.getId()).get();
        // Disconnect from session so that the updates on updatedComponentDetails are not directly saved in db
        em.detach(updatedComponentDetails);
        updatedComponentDetails
            .server(UPDATED_SERVER)
            .port(UPDATED_PORT)
            .url(UPDATED_URL);
        ComponentDetailsDTO componentDetailsDTO = componentDetailsMapper.toDto(updatedComponentDetails);

        restComponentDetailsMockMvc.perform(put("/api/component-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(componentDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the ComponentDetails in the database
        List<ComponentDetails> componentDetailsList = componentDetailsRepository.findAll();
        assertThat(componentDetailsList).hasSize(databaseSizeBeforeUpdate);
        ComponentDetails testComponentDetails = componentDetailsList.get(componentDetailsList.size() - 1);
        assertThat(testComponentDetails.getServer()).isEqualTo(UPDATED_SERVER);
        assertThat(testComponentDetails.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testComponentDetails.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingComponentDetails() throws Exception {
        int databaseSizeBeforeUpdate = componentDetailsRepository.findAll().size();

        // Create the ComponentDetails
        ComponentDetailsDTO componentDetailsDTO = componentDetailsMapper.toDto(componentDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComponentDetailsMockMvc.perform(put("/api/component-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(componentDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComponentDetails in the database
        List<ComponentDetails> componentDetailsList = componentDetailsRepository.findAll();
        assertThat(componentDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComponentDetails() throws Exception {
        // Initialize the database
        componentDetailsRepository.saveAndFlush(componentDetails);

        int databaseSizeBeforeDelete = componentDetailsRepository.findAll().size();

        // Delete the componentDetails
        restComponentDetailsMockMvc.perform(delete("/api/component-details/{id}", componentDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ComponentDetails> componentDetailsList = componentDetailsRepository.findAll();
        assertThat(componentDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
