package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FlowManagerApp;
import com.mycompany.myapp.domain.ComponentCategory;
import com.mycompany.myapp.repository.ComponentCategoryRepository;
import com.mycompany.myapp.service.ComponentCategoryService;
import com.mycompany.myapp.service.dto.ComponentCategoryDTO;
import com.mycompany.myapp.service.mapper.ComponentCategoryMapper;

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
 * Integration tests for the {@link ComponentCategoryResource} REST controller.
 */
@SpringBootTest(classes = FlowManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ComponentCategoryResourceIT {

    private static final String DEFAULT_SHORT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_LONG_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LONG_LABEL = "BBBBBBBBBB";

    @Autowired
    private ComponentCategoryRepository componentCategoryRepository;

    @Autowired
    private ComponentCategoryMapper componentCategoryMapper;

    @Autowired
    private ComponentCategoryService componentCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComponentCategoryMockMvc;

    private ComponentCategory componentCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComponentCategory createEntity(EntityManager em) {
        ComponentCategory componentCategory = new ComponentCategory()
            .shortLabel(DEFAULT_SHORT_LABEL)
            .label(DEFAULT_LABEL)
            .longLabel(DEFAULT_LONG_LABEL);
        return componentCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComponentCategory createUpdatedEntity(EntityManager em) {
        ComponentCategory componentCategory = new ComponentCategory()
            .shortLabel(UPDATED_SHORT_LABEL)
            .label(UPDATED_LABEL)
            .longLabel(UPDATED_LONG_LABEL);
        return componentCategory;
    }

    @BeforeEach
    public void initTest() {
        componentCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createComponentCategory() throws Exception {
        int databaseSizeBeforeCreate = componentCategoryRepository.findAll().size();
        // Create the ComponentCategory
        ComponentCategoryDTO componentCategoryDTO = componentCategoryMapper.toDto(componentCategory);
        restComponentCategoryMockMvc.perform(post("/api/component-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(componentCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the ComponentCategory in the database
        List<ComponentCategory> componentCategoryList = componentCategoryRepository.findAll();
        assertThat(componentCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        ComponentCategory testComponentCategory = componentCategoryList.get(componentCategoryList.size() - 1);
        assertThat(testComponentCategory.getShortLabel()).isEqualTo(DEFAULT_SHORT_LABEL);
        assertThat(testComponentCategory.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testComponentCategory.getLongLabel()).isEqualTo(DEFAULT_LONG_LABEL);
    }

    @Test
    @Transactional
    public void createComponentCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = componentCategoryRepository.findAll().size();

        // Create the ComponentCategory with an existing ID
        componentCategory.setId(1L);
        ComponentCategoryDTO componentCategoryDTO = componentCategoryMapper.toDto(componentCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComponentCategoryMockMvc.perform(post("/api/component-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(componentCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComponentCategory in the database
        List<ComponentCategory> componentCategoryList = componentCategoryRepository.findAll();
        assertThat(componentCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllComponentCategories() throws Exception {
        // Initialize the database
        componentCategoryRepository.saveAndFlush(componentCategory);

        // Get all the componentCategoryList
        restComponentCategoryMockMvc.perform(get("/api/component-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(componentCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].shortLabel").value(hasItem(DEFAULT_SHORT_LABEL)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].longLabel").value(hasItem(DEFAULT_LONG_LABEL)));
    }
    
    @Test
    @Transactional
    public void getComponentCategory() throws Exception {
        // Initialize the database
        componentCategoryRepository.saveAndFlush(componentCategory);

        // Get the componentCategory
        restComponentCategoryMockMvc.perform(get("/api/component-categories/{id}", componentCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(componentCategory.getId().intValue()))
            .andExpect(jsonPath("$.shortLabel").value(DEFAULT_SHORT_LABEL))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.longLabel").value(DEFAULT_LONG_LABEL));
    }
    @Test
    @Transactional
    public void getNonExistingComponentCategory() throws Exception {
        // Get the componentCategory
        restComponentCategoryMockMvc.perform(get("/api/component-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComponentCategory() throws Exception {
        // Initialize the database
        componentCategoryRepository.saveAndFlush(componentCategory);

        int databaseSizeBeforeUpdate = componentCategoryRepository.findAll().size();

        // Update the componentCategory
        ComponentCategory updatedComponentCategory = componentCategoryRepository.findById(componentCategory.getId()).get();
        // Disconnect from session so that the updates on updatedComponentCategory are not directly saved in db
        em.detach(updatedComponentCategory);
        updatedComponentCategory
            .shortLabel(UPDATED_SHORT_LABEL)
            .label(UPDATED_LABEL)
            .longLabel(UPDATED_LONG_LABEL);
        ComponentCategoryDTO componentCategoryDTO = componentCategoryMapper.toDto(updatedComponentCategory);

        restComponentCategoryMockMvc.perform(put("/api/component-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(componentCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the ComponentCategory in the database
        List<ComponentCategory> componentCategoryList = componentCategoryRepository.findAll();
        assertThat(componentCategoryList).hasSize(databaseSizeBeforeUpdate);
        ComponentCategory testComponentCategory = componentCategoryList.get(componentCategoryList.size() - 1);
        assertThat(testComponentCategory.getShortLabel()).isEqualTo(UPDATED_SHORT_LABEL);
        assertThat(testComponentCategory.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testComponentCategory.getLongLabel()).isEqualTo(UPDATED_LONG_LABEL);
    }

    @Test
    @Transactional
    public void updateNonExistingComponentCategory() throws Exception {
        int databaseSizeBeforeUpdate = componentCategoryRepository.findAll().size();

        // Create the ComponentCategory
        ComponentCategoryDTO componentCategoryDTO = componentCategoryMapper.toDto(componentCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComponentCategoryMockMvc.perform(put("/api/component-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(componentCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComponentCategory in the database
        List<ComponentCategory> componentCategoryList = componentCategoryRepository.findAll();
        assertThat(componentCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComponentCategory() throws Exception {
        // Initialize the database
        componentCategoryRepository.saveAndFlush(componentCategory);

        int databaseSizeBeforeDelete = componentCategoryRepository.findAll().size();

        // Delete the componentCategory
        restComponentCategoryMockMvc.perform(delete("/api/component-categories/{id}", componentCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ComponentCategory> componentCategoryList = componentCategoryRepository.findAll();
        assertThat(componentCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
