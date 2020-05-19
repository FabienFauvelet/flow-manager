package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ArchitectureComponentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ArchitectureComponent} and its DTO {@link ArchitectureComponentDTO}.
 */
@Mapper(componentModel = "spring", uses = {ComponentCategoryMapper.class, ProjectMapper.class})
public interface ArchitectureComponentMapper extends EntityMapper<ArchitectureComponentDTO, ArchitectureComponent> {

    @Mapping(source = "componentCategory.id", target = "componentCategoryId")
    @Mapping(source = "components.id", target = "componentsId")
    ArchitectureComponentDTO toDto(ArchitectureComponent architectureComponent);

    @Mapping(target = "componentDetails", ignore = true)
    @Mapping(target = "removeComponentDetails", ignore = true)
    @Mapping(source = "componentCategoryId", target = "componentCategory")
    @Mapping(source = "componentsId", target = "components")
    @Mapping(target = "componentFroms", ignore = true)
    @Mapping(target = "removeComponentFrom", ignore = true)
    @Mapping(target = "componentTos", ignore = true)
    @Mapping(target = "removeComponentTo", ignore = true)
    @Mapping(target = "components", ignore = true)
    @Mapping(target = "removeComponent", ignore = true)
    ArchitectureComponent toEntity(ArchitectureComponentDTO architectureComponentDTO);

    default ArchitectureComponent fromId(Long id) {
        if (id == null) {
            return null;
        }
        ArchitectureComponent architectureComponent = new ArchitectureComponent();
        architectureComponent.setId(id);
        return architectureComponent;
    }
}
