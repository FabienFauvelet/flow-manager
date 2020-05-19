package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Flow} entity.
 */
public class FlowDTO implements Serializable {
    
    private Long id;

    private String description;

    private Boolean status;


    private Long architectureComponentId;

    private Long architectureComponentId;

    private Long flowsId;
    
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

    public Long getArchitectureComponentId() {
        return architectureComponentId;
    }

    public void setArchitectureComponentId(Long architectureComponentId) {
        this.architectureComponentId = architectureComponentId;
    }

    public Long getArchitectureComponentId() {
        return architectureComponentId;
    }

    public void setArchitectureComponentId(Long architectureComponentId) {
        this.architectureComponentId = architectureComponentId;
    }

    public Long getFlowsId() {
        return flowsId;
    }

    public void setFlowsId(Long projectId) {
        this.flowsId = projectId;
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
            ", architectureComponentId=" + getArchitectureComponentId() +
            ", architectureComponentId=" + getArchitectureComponentId() +
            ", flowsId=" + getFlowsId() +
            "}";
    }
}
