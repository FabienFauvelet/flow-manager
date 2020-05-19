package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ComponentCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ComponentCategory}.
 */
public interface ComponentCategoryService {

    /**
     * Save a componentCategory.
     *
     * @param componentCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    ComponentCategoryDTO save(ComponentCategoryDTO componentCategoryDTO);

    /**
     * Get all the componentCategories.
     *
     * @return the list of entities.
     */
    List<ComponentCategoryDTO> findAll();


    /**
     * Get the "id" componentCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComponentCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" componentCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
