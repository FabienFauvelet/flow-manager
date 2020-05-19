package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ArchitectureComponent} entity.
 */
public class ArchitectureComponentDTO implements Serializable {
    
    private Long id;

    private String name;


    private Long categoryId;

    private Long projectId;
    
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long componentCategoryId) {
        this.categoryId = componentCategoryId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
            ", categoryId=" + getCategoryId() +
            ", projectId=" + getProjectId() +
            "}";
    }
}
