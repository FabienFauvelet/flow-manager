package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FlowManagerApp;
import com.mycompany.myapp.domain.Environment;
import com.mycompany.myapp.repository.EnvironmentRepository;
import com.mycompany.myapp.service.EnvironmentService;
import com.mycompany.myapp.service.dto.EnvironmentDTO;
import com.mycompany.myapp.service.mapper.EnvironmentMapper;

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
 * Integration tests for the {@link EnvironmentResource} REST controller.
 */
@SpringBootTest(classes = FlowManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EnvironmentResourceIT {

    private static final String DEFAULT_SHORT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_LONG_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LONG_LABEL = "BBBBBBBBBB";

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private EnvironmentMapper environmentMapper;

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEnvironmentMockMvc;

    private Environment environment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Environment createEntity(EntityManager em) {
        Environment environment = new Environment()
            .shortLabel(DEFAULT_SHORT_LABEL)
            .label(DEFAULT_LABEL)
            .longLabel(DEFAULT_LONG_LABEL);
        return environment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Environment createUpdatedEntity(EntityManager em) {
        Environment environment = new Environment()
            .shortLabel(UPDATED_SHORT_LABEL)
            .label(UPDATED_LABEL)
            .longLabel(UPDATED_LONG_LABEL);
        return environment;
    }

    @BeforeEach
    public void initTest() {
        environment = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnvironment() throws Exception {
        int databaseSizeBeforeCreate = environmentRepository.findAll().size();
        // Create the Environment
        EnvironmentDTO environmentDTO = environmentMapper.toDto(environment);
        restEnvironmentMockMvc.perform(post("/api/environments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(environmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Environment in the database
        List<Environment> environmentList = environmentRepository.findAll();
        assertThat(environmentList).hasSize(databaseSizeBeforeCreate + 1);
        Environment testEnvironment = environmentList.get(environmentList.size() - 1);
        assertThat(testEnvironment.getShortLabel()).isEqualTo(DEFAULT_SHORT_LABEL);
        assertThat(testEnvironment.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testEnvironment.getLongLabel()).isEqualTo(DEFAULT_LONG_LABEL);
    }

    @Test
    @Transactional
    public void createEnvironmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = environmentRepository.findAll().size();

        // Create the Environment with an existing ID
        environment.setId(1L);
        EnvironmentDTO environmentDTO = environmentMapper.toDto(environment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnvironmentMockMvc.perform(post("/api/environments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(environmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Environment in the database
        List<Environment> environmentList = environmentRepository.findAll();
        assertThat(environmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnvironments() throws Exception {
        // Initialize the database
        environmentRepository.saveAndFlush(environment);

        // Get all the environmentList
        restEnvironmentMockMvc.perform(get("/api/environments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(environment.getId().intValue())))
            .andExpect(jsonPath("$.[*].shortLabel").value(hasItem(DEFAULT_SHORT_LABEL)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].longLabel").value(hasItem(DEFAULT_LONG_LABEL)));
    }
    
    @Test
    @Transactional
    public void getEnvironment() throws Exception {
        // Initialize the database
        environmentRepository.saveAndFlush(environment);

        // Get the environment
        restEnvironmentMockMvc.perform(get("/api/environments/{id}", environment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(environment.getId().intValue()))
            .andExpect(jsonPath("$.shortLabel").value(DEFAULT_SHORT_LABEL))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.longLabel").value(DEFAULT_LONG_LABEL));
    }
    @Test
    @Transactional
    public void getNonExistingEnvironment() throws Exception {
        // Get the environment
        restEnvironmentMockMvc.perform(get("/api/environments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnvironment() throws Exception {
        // Initialize the database
        environmentRepository.saveAndFlush(environment);

        int databaseSizeBeforeUpdate = environmentRepository.findAll().size();

        // Update the environment
        Environment updatedEnvironment = environmentRepository.findById(environment.getId()).get();
        // Disconnect from session so that the updates on updatedEnvironment are not directly saved in db
        em.detach(updatedEnvironment);
        updatedEnvironment
            .shortLabel(UPDATED_SHORT_LABEL)
            .label(UPDATED_LABEL)
            .longLabel(UPDATED_LONG_LABEL);
        EnvironmentDTO environmentDTO = environmentMapper.toDto(updatedEnvironment);

        restEnvironmentMockMvc.perform(put("/api/environments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(environmentDTO)))
            .andExpect(status().isOk());

        // Validate the Environment in the database
        List<Environment> environmentList = environmentRepository.findAll();
        assertThat(environmentList).hasSize(databaseSizeBeforeUpdate);
        Environment testEnvironment = environmentList.get(environmentList.size() - 1);
        assertThat(testEnvironment.getShortLabel()).isEqualTo(UPDATED_SHORT_LABEL);
        assertThat(testEnvironment.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testEnvironment.getLongLabel()).isEqualTo(UPDATED_LONG_LABEL);
    }

    @Test
    @Transactional
    public void updateNonExistingEnvironment() throws Exception {
        int databaseSizeBeforeUpdate = environmentRepository.findAll().size();

        // Create the Environment
        EnvironmentDTO environmentDTO = environmentMapper.toDto(environment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnvironmentMockMvc.perform(put("/api/environments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(environmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Environment in the database
        List<Environment> environmentList = environmentRepository.findAll();
        assertThat(environmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnvironment() throws Exception {
        // Initialize the database
        environmentRepository.saveAndFlush(environment);

        int databaseSizeBeforeDelete = environmentRepository.findAll().size();

        // Delete the environment
        restEnvironmentMockMvc.perform(delete("/api/environments/{id}", environment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Environment> environmentList = environmentRepository.findAll();
        assertThat(environmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
