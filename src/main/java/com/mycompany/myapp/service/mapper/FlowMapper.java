package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.FlowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Flow} and its DTO {@link FlowDTO}.
 */
@Mapper(componentModel = "spring", uses = {ArchitectureComponentMapper.class, ProjectMapper.class})
public interface FlowMapper extends EntityMapper<FlowDTO, Flow> {

    @Mapping(source = "architectureComponent.id", target = "architectureComponentId")
    @Mapping(source = "architectureComponent.id", target = "architectureComponentId")
    @Mapping(source = "flows.id", target = "flowsId")
    FlowDTO toDto(Flow flow);

    @Mapping(source = "architectureComponentId", target = "architectureComponent")
    @Mapping(source = "architectureComponentId", target = "architectureComponent")
    @Mapping(source = "flowsId", target = "flows")
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
