package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Flow} entity.
 */
public class FlowDTO implements Serializable {
    
    private Long id;

    private String description;

    private Boolean status;


    private Long componentFromId;

    private Long componentToId;

    private Long projectId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getComponentFromId() {
        return componentFromId;
    }

    public void setComponentFromId(Long architectureComponentId) {
        this.componentFromId = architectureComponentId;
    }

    public Long getComponentToId() {
        return componentToId;
    }

    public void setComponentToId(Long architectureComponentId) {
        this.componentToId = architectureComponentId;
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
        if (!(o instanceof FlowDTO)) {
            return false;
        }

        return id != null && id.equals(((FlowDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", status='" + isStatus() + "'" +
            ", componentFromId=" + getComponentFromId() +
            ", componentToId=" + getComponentToId() +
            ", projectId=" + getProjectId() +
            "}";
    }
}
