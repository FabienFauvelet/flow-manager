package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EnvironmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Environment} and its DTO {@link EnvironmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnvironmentMapper extends EntityMapper<EnvironmentDTO, Environment> {


    @Mapping(target = "componentDetails", ignore = true)
    @Mapping(target = "removeComponentDetails", ignore = true)
    @Mapping(target = "environments", ignore = true)
    @Mapping(target = "removeEnvironment", ignore = true)
    Environment toEntity(EnvironmentDTO environmentDTO);

    default Environment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Environment environment = new Environment();
        environment.setId(id);
        return environment;
    }
}
