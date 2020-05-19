package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ComponentCategoryService;
import com.mycompany.myapp.domain.ComponentCategory;
import com.mycompany.myapp.repository.ComponentCategoryRepository;
import com.mycompany.myapp.service.dto.ComponentCategoryDTO;
import com.mycompany.myapp.service.mapper.ComponentCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ComponentCategory}.
 */
@Service
@Transactional
public class ComponentCategoryServiceImpl implements ComponentCategoryService {

    private final Logger log = LoggerFactory.getLogger(ComponentCategoryServiceImpl.class);

    private final ComponentCategoryRepository componentCategoryRepository;

    private final ComponentCategoryMapper componentCategoryMapper;

    public ComponentCategoryServiceImpl(ComponentCategoryRepository componentCategoryRepository, ComponentCategoryMapper componentCategoryMapper) {
        this.componentCategoryRepository = componentCategoryRepository;
        this.componentCategoryMapper = componentCategoryMapper;
    }

    /**
     * Save a componentCategory.
     *
     * @param componentCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ComponentCategoryDTO save(ComponentCategoryDTO componentCategoryDTO) {
        log.debug("Request to save ComponentCategory : {}", componentCategoryDTO);
        ComponentCategory componentCategory = componentCategoryMapper.toEntity(componentCategoryDTO);
        componentCategory = componentCategoryRepository.save(componentCategory);
        return componentCategoryMapper.toDto(componentCategory);
    }

    /**
     * Get all the componentCategories.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ComponentCategoryDTO> findAll() {
        log.debug("Request to get all ComponentCategories");
        return componentCategoryRepository.findAll().stream()
            .map(componentCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one componentCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComponentCategoryDTO> findOne(Long id) {
        log.debug("Request to get ComponentCategory : {}", id);
        return componentCategoryRepository.findById(id)
            .map(componentCategoryMapper::toDto);
    }

    /**
     * Delete the componentCategory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComponentCategory : {}", id);

        componentCategoryRepository.deleteById(id);
    }
}
