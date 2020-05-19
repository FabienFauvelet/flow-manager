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
    @Mapping(source = "architectureComponent.id", target = "architectureComponentId")
    @Mapping(source = "detailsByEnvironement.id", target = "detailsByEnvironementId")
    @Mapping(source = "componentDetails.id", target = "componentDetailsId")
    ComponentDetailsDTO toDto(ComponentDetails componentDetails);

    @Mapping(source = "environmentId", target = "environment")
    @Mapping(source = "architectureComponentId", target = "architectureComponent")
    @Mapping(source = "detailsByEnvironementId", target = "detailsByEnvironement")
    @Mapping(source = "componentDetailsId", target = "componentDetails")
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
