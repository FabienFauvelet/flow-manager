package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ComponentCategoryService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ComponentCategoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ComponentCategory}.
 */
@RestController
@RequestMapping("/api")
public class ComponentCategoryResource {

    private final Logger log = LoggerFactory.getLogger(ComponentCategoryResource.class);

    private static final String ENTITY_NAME = "componentCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComponentCategoryService componentCategoryService;

    public ComponentCategoryResource(ComponentCategoryService componentCategoryService) {
        this.componentCategoryService = componentCategoryService;
    }

    /**
     * {@code POST  /component-categories} : Create a new componentCategory.
     *
     * @param componentCategoryDTO the componentCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new componentCategoryDTO, or with status {@code 400 (Bad Request)} if the componentCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/component-categories")
    public ResponseEntity<ComponentCategoryDTO> createComponentCategory(@RequestBody ComponentCategoryDTO componentCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save ComponentCategory : {}", componentCategoryDTO);
        if (componentCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new componentCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComponentCategoryDTO result = componentCategoryService.save(componentCategoryDTO);
        return ResponseEntity.created(new URI("/api/component-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /component-categories} : Updates an existing componentCategory.
     *
     * @param componentCategoryDTO the componentCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated componentCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the componentCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the componentCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/component-categories")
    public ResponseEntity<ComponentCategoryDTO> updateComponentCategory(@RequestBody ComponentCategoryDTO componentCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update ComponentCategory : {}", componentCategoryDTO);
        if (componentCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComponentCategoryDTO result = componentCategoryService.save(componentCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, componentCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /component-categories} : get all the componentCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of componentCategories in body.
     */
    @GetMapping("/component-categories")
    public List<ComponentCategoryDTO> getAllComponentCategories() {
        log.debug("REST request to get all ComponentCategories");
        return componentCategoryService.findAll();
    }

    /**
     * {@code GET  /component-categories/:id} : get the "id" componentCategory.
     *
     * @param id the id of the componentCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the componentCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/component-categories/{id}")
    public ResponseEntity<ComponentCategoryDTO> getComponentCategory(@PathVariable Long id) {
        log.debug("REST request to get ComponentCategory : {}", id);
        Optional<ComponentCategoryDTO> componentCategoryDTO = componentCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(componentCategoryDTO);
    }

    /**
     * {@code DELETE  /component-categories/:id} : delete the "id" componentCategory.
     *
     * @param id the id of the componentCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/component-categories/{id}")
    public ResponseEntity<Void> deleteComponentCategory(@PathVariable Long id) {
        log.debug("REST request to delete ComponentCategory : {}", id);

        componentCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
