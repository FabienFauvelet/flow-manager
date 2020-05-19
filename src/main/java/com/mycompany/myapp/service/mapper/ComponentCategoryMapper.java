package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ComponentCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ComponentCategory} and its DTO {@link ComponentCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ComponentCategoryMapper extends EntityMapper<ComponentCategoryDTO, ComponentCategory> {



    default ComponentCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComponentCategory componentCategory = new ComponentCategory();
        componentCategory.setId(id);
        return componentCategory;
    }
}
