package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ArchitectureComponentService;
import com.mycompany.myapp.domain.ArchitectureComponent;
import com.mycompany.myapp.repository.ArchitectureComponentRepository;
import com.mycompany.myapp.service.dto.ArchitectureComponentDTO;
import com.mycompany.myapp.service.mapper.ArchitectureComponentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ArchitectureComponent}.
 */
@Service
@Transactional
public class ArchitectureComponentServiceImpl implements ArchitectureComponentService {

    private final Logger log = LoggerFactory.getLogger(ArchitectureComponentServiceImpl.class);

    private final ArchitectureComponentRepository architectureComponentRepository;

    private final ArchitectureComponentMapper architectureComponentMapper;

    public ArchitectureComponentServiceImpl(ArchitectureComponentRepository architectureComponentRepository, ArchitectureComponentMapper architectureComponentMapper) {
        this.architectureComponentRepository = architectureComponentRepository;
        this.architectureComponentMapper = architectureComponentMapper;
    }

    /**
     * Save a architectureComponent.
     *
     * @param architectureComponentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ArchitectureComponentDTO save(ArchitectureComponentDTO architectureComponentDTO) {
        log.debug("Request to save ArchitectureComponent : {}", architectureComponentDTO);
        ArchitectureComponent architectureComponent = architectureComponentMapper.toEntity(architectureComponentDTO);
        architectureComponent = architectureComponentRepository.save(architectureComponent);
        return architectureComponentMapper.toDto(architectureComponent);
    }

    /**
     * Get all the architectureComponents.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ArchitectureComponentDTO> findAll() {
        log.debug("Request to get all ArchitectureComponents");
        return architectureComponentRepository.findAll().stream()
            .map(architectureComponentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one architectureComponent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ArchitectureComponentDTO> findOne(Long id) {
        log.debug("Request to get ArchitectureComponent : {}", id);
        return architectureComponentRepository.findById(id)
            .map(architectureComponentMapper::toDto);
    }

    /**
     * Delete the architectureComponent by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ArchitectureComponent : {}", id);

        architectureComponentRepository.deleteById(id);
    }
}
