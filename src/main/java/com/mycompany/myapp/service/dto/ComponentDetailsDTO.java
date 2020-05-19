package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ComponentDetails} entity.
 */
public class ComponentDetailsDTO implements Serializable {
    
    private Long id;

    private String server;

    private String port;

    private String url;


    private Long environmentId;

    private Long componentId;

    private Long architectureComponentId;

    private Long environmentId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long architectureComponentId) {
        this.componentId = architectureComponentId;
    }

    public Long getArchitectureComponentId() {
        return architectureComponentId;
    }

    public void setArchitectureComponentId(Long architectureComponentId) {
        this.architectureComponentId = architectureComponentId;
    }

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComponentDetailsDTO)) {
            return false;
        }

        return id != null && id.equals(((ComponentDetailsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComponentDetailsDTO{" +
            "id=" + getId() +
            ", server='" + getServer() + "'" +
            ", port='" + getPort() + "'" +
            ", url='" + getUrl() + "'" +
            ", environmentId=" + getEnvironmentId() +
            ", componentId=" + getComponentId() +
            ", architectureComponentId=" + getArchitectureComponentId() +
            ", environmentId=" + getEnvironmentId() +
            "}";
    }
}
