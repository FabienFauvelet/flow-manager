package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.FlowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Flow} and its DTO {@link FlowDTO}.
 */
@Mapper(componentModel = "spring", uses = {ArchitectureComponentMapper.class, ProjectMapper.class})
public interface FlowMapper extends EntityMapper<FlowDTO, Flow> {

    @Mapping(source = "componentFrom.id", target = "componentFromId")
    @Mapping(source = "componentTo.id", target = "componentToId")
    @Mapping(source = "project.id", target = "projectId")
    FlowDTO toDto(Flow flow);

    @Mapping(source = "componentFromId", target = "componentFrom")
    @Mapping(source = "componentToId", target = "componentTo")
    @Mapping(source = "projectId", target = "project")
    Flow toEntity(FlowDTO flowDTO);

    default Flow fromId(Long id) {
        if (id == null) {
            return null;
        }
        Flow flow = new Flow();
        flow.setId(id);
        return flow;
    }
}
