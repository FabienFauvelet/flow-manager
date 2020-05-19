package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ArchitectureComponent} entity.
 */
public class ArchitectureComponentDTO implements Serializable {
    
    private Long id;

    private String name;


    private Long componentCategoryId;

    private Long componentsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getComponentCategoryId() {
        return componentCategoryId;
    }

    public void setComponentCategoryId(Long componentCategoryId) {
        this.componentCategoryId = componentCategoryId;
    }

    public Long getComponentsId() {
        return componentsId;
    }

    public void setComponentsId(Long projectId) {
        this.componentsId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArchitectureComponentDTO)) {
            return false;
        }

        return id != null && id.equals(((ArchitectureComponentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArchitectureComponentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", componentCategoryId=" + getComponentCategoryId() +
            ", componentsId=" + getComponentsId() +
            "}";
    }
}
