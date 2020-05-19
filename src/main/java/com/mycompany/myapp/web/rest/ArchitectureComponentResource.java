package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ArchitectureComponentService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ArchitectureComponentDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ArchitectureComponent}.
 */
@RestController
@RequestMapping("/api")
public class ArchitectureComponentResource {

    private final Logger log = LoggerFactory.getLogger(ArchitectureComponentResource.class);

    private static final String ENTITY_NAME = "architectureComponent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArchitectureComponentService architectureComponentService;

    public ArchitectureComponentResource(ArchitectureComponentService architectureComponentService) {
        this.architectureComponentService = architectureComponentService;
    }

    /**
     * {@code POST  /architecture-components} : Create a new architectureComponent.
     *
     * @param architectureComponentDTO the architectureComponentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new architectureComponentDTO, or with status {@code 400 (Bad Request)} if the architectureComponent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/architecture-components")
    public ResponseEntity<ArchitectureComponentDTO> createArchitectureComponent(@RequestBody ArchitectureComponentDTO architectureComponentDTO) throws URISyntaxException {
        log.debug("REST request to save ArchitectureComponent : {}", architectureComponentDTO);
        if (architectureComponentDTO.getId() != null) {
            throw new BadRequestAlertException("A new architectureComponent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArchitectureComponentDTO result = architectureComponentService.save(architectureComponentDTO);
        return ResponseEntity.created(new URI("/api/architecture-components/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /architecture-components} : Updates an existing architectureComponent.
     *
     * @param architectureComponentDTO the architectureComponentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated architectureComponentDTO,
     * or with status {@code 400 (Bad Request)} if the architectureComponentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the architectureComponentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/architecture-components")
    public ResponseEntity<ArchitectureComponentDTO> updateArchitectureComponent(@RequestBody ArchitectureComponentDTO architectureComponentDTO) throws URISyntaxException {
        log.debug("REST request to update ArchitectureComponent : {}", architectureComponentDTO);
        if (architectureComponentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ArchitectureComponentDTO result = architectureComponentService.save(architectureComponentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, architectureComponentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /architecture-components} : get all the architectureComponents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of architectureComponents in body.
     */
    @GetMapping("/architecture-components")
    public List<ArchitectureComponentDTO> getAllArchitectureComponents() {
        log.debug("REST request to get all ArchitectureComponents");
        return architectureComponentService.findAll();
    }

    /**
     * {@code GET  /architecture-components/:id} : get the "id" architectureComponent.
     *
     * @param id the id of the architectureComponentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the architectureComponentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/architecture-components/{id}")
    public ResponseEntity<ArchitectureComponentDTO> getArchitectureComponent(@PathVariable Long id) {
        log.debug("REST request to get ArchitectureComponent : {}", id);
        Optional<ArchitectureComponentDTO> architectureComponentDTO = architectureComponentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(architectureComponentDTO);
    }

    /**
     * {@code DELETE  /architecture-components/:id} : delete the "id" architectureComponent.
     *
     * @param id the id of the architectureComponentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/architecture-components/{id}")
    public ResponseEntity<Void> deleteArchitectureComponent(@PathVariable Long id) {
        log.debug("REST request to delete ArchitectureComponent : {}", id);

        architectureComponentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
