package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ComponentDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ComponentDetails} and its DTO {@link ComponentDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnvironmentMapper.class, ArchitectureComponentMapper.class})
public interface ComponentDetailsMapper extends EntityMapper<ComponentDetailsDTO, ComponentDetails> {

    @Mapping(source = "environment.id", target = "environmentId")
    @Mapping(source = "component.id", target = "componentId")
    @Mapping(source = "architectureComponent.id", target = "architectureComponentId")
    @Mapping(source = "environment.id", target = "environmentId")
    ComponentDetailsDTO toDto(ComponentDetails componentDetails);

    @Mapping(source = "environmentId", target = "environment")
    @Mapping(source = "componentId", target = "component")
    @Mapping(source = "architectureComponentId", target = "architectureComponent")
    @Mapping(source = "environmentId", target = "environment")
    ComponentDetails toEntity(ComponentDetailsDTO componentDetailsDTO);

    default ComponentDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComponentDetails componentDetails = new ComponentDetails();
        componentDetails.setId(id);
        return componentDetails;
    }
}
