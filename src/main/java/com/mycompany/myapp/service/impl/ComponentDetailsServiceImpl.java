package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ComponentDetailsService;
import com.mycompany.myapp.domain.ComponentDetails;
import com.mycompany.myapp.repository.ComponentDetailsRepository;
import com.mycompany.myapp.service.dto.ComponentDetailsDTO;
import com.mycompany.myapp.service.mapper.ComponentDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ComponentDetails}.
 */
@Service
@Transactional
public class ComponentDetailsServiceImpl implements ComponentDetailsService {

    private final Logger log = LoggerFactory.getLogger(ComponentDetailsServiceImpl.class);

    private final ComponentDetailsRepository componentDetailsRepository;

    private final ComponentDetailsMapper componentDetailsMapper;

    public ComponentDetailsServiceImpl(ComponentDetailsRepository componentDetailsRepository, ComponentDetailsMapper componentDetailsMapper) {
        this.componentDetailsRepository = componentDetailsRepository;
        this.componentDetailsMapper = componentDetailsMapper;
    }

    /**
     * Save a componentDetails.
     *
     * @param componentDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ComponentDetailsDTO save(ComponentDetailsDTO componentDetailsDTO) {
        log.debug("Request to save ComponentDetails : {}", componentDetailsDTO);
        ComponentDetails componentDetails = componentDetailsMapper.toEntity(componentDetailsDTO);
        componentDetails = componentDetailsRepository.save(componentDetails);
        return componentDetailsMapper.toDto(componentDetails);
    }

    /**
     * Get all the componentDetails.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ComponentDetailsDTO> findAll() {
        log.debug("Request to get all ComponentDetails");
        return componentDetailsRepository.findAll().stream()
            .map(componentDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one componentDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComponentDetailsDTO> findOne(Long id) {
        log.debug("Request to get ComponentDetails : {}", id);
        return componentDetailsRepository.findById(id)
            .map(componentDetailsMapper::toDto);
    }

    /**
     * Delete the componentDetails by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComponentDetails : {}", id);

        componentDetailsRepository.deleteById(id);
    }
}
