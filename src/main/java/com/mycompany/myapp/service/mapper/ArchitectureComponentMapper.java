package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ArchitectureComponentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ArchitectureComponent} and its DTO {@link ArchitectureComponentDTO}.
 */
@Mapper(componentModel = "spring", uses = {ComponentCategoryMapper.class, ProjectMapper.class})
public interface ArchitectureComponentMapper extends EntityMapper<ArchitectureComponentDTO, ArchitectureComponent> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "project.id", target = "projectId")
    ArchitectureComponentDTO toDto(ArchitectureComponent architectureComponent);

    @Mapping(target = "detailsByEnvironements", ignore = true)
    @Mapping(target = "removeDetailsByEnvironement", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "projectId", target = "project")
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
