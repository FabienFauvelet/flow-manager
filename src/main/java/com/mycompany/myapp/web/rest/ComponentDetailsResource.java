package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ComponentDetailsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ComponentDetailsDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ComponentDetails}.
 */
@RestController
@RequestMapping("/api")
public class ComponentDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ComponentDetailsResource.class);

    private static final String ENTITY_NAME = "componentDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComponentDetailsService componentDetailsService;

    public ComponentDetailsResource(ComponentDetailsService componentDetailsService) {
        this.componentDetailsService = componentDetailsService;
    }

    /**
     * {@code POST  /component-details} : Create a new componentDetails.
     *
     * @param componentDetailsDTO the componentDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new componentDetailsDTO, or with status {@code 400 (Bad Request)} if the componentDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/component-details")
    public ResponseEntity<ComponentDetailsDTO> createComponentDetails(@RequestBody ComponentDetailsDTO componentDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save ComponentDetails : {}", componentDetailsDTO);
        if (componentDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new componentDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComponentDetailsDTO result = componentDetailsService.save(componentDetailsDTO);
        return ResponseEntity.created(new URI("/api/component-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /component-details} : Updates an existing componentDetails.
     *
     * @param componentDetailsDTO the componentDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated componentDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the componentDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the componentDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/component-details")
    public ResponseEntity<ComponentDetailsDTO> updateComponentDetails(@RequestBody ComponentDetailsDTO componentDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update ComponentDetails : {}", componentDetailsDTO);
        if (componentDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComponentDetailsDTO result = componentDetailsService.save(componentDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, componentDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /component-details} : get all the componentDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of componentDetails in body.
     */
    @GetMapping("/component-details")
    public List<ComponentDetailsDTO> getAllComponentDetails() {
        log.debug("REST request to get all ComponentDetails");
        return componentDetailsService.findAll();
    }

    /**
     * {@code GET  /component-details/:id} : get the "id" componentDetails.
     *
     * @param id the id of the componentDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the componentDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/component-details/{id}")
    public ResponseEntity<ComponentDetailsDTO> getComponentDetails(@PathVariable Long id) {
        log.debug("REST request to get ComponentDetails : {}", id);
        Optional<ComponentDetailsDTO> componentDetailsDTO = componentDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(componentDetailsDTO);
    }

    /**
     * {@code DELETE  /component-details/:id} : delete the "id" componentDetails.
     *
     * @param id the id of the componentDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/component-details/{id}")
    public ResponseEntity<Void> deleteComponentDetails(@PathVariable Long id) {
        log.debug("REST request to delete ComponentDetails : {}", id);

        componentDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
