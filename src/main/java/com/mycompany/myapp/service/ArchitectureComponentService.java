package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ArchitectureComponentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ArchitectureComponent}.
 */
public interface ArchitectureComponentService {

    /**
     * Save a architectureComponent.
     *
     * @param architectureComponentDTO the entity to save.
     * @return the persisted entity.
     */
    ArchitectureComponentDTO save(ArchitectureComponentDTO architectureComponentDTO);

    /**
     * Get all the architectureComponents.
     *
     * @return the list of entities.
     */
    List<ArchitectureComponentDTO> findAll();


    /**
     * Get the "id" architectureComponent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArchitectureComponentDTO> findOne(Long id);

    /**
     * Delete the "id" architectureComponent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
