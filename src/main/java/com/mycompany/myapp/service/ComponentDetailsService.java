package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ComponentDetailsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ComponentDetails}.
 */
public interface ComponentDetailsService {

    /**
     * Save a componentDetails.
     *
     * @param componentDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    ComponentDetailsDTO save(ComponentDetailsDTO componentDetailsDTO);

    /**
     * Get all the componentDetails.
     *
     * @return the list of entities.
     */
    List<ComponentDetailsDTO> findAll();


    /**
     * Get the "id" componentDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComponentDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" componentDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
