package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ComponentDetails.
 */
@Entity
@Table(name = "component_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ComponentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "server")
    private String server;

    @Column(name = "port")
    private String port;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JsonIgnoreProperties(value = "componentDetails", allowSetters = true)
    private Environment environment;

    @ManyToOne
    @JsonIgnoreProperties(value = "componentDetails", allowSetters = true)
    private ArchitectureComponent component;

    @ManyToOne
    @JsonIgnoreProperties(value = "detailsByEnvironements", allowSetters = true)
    private ArchitectureComponent architectureComponent;

    @ManyToOne
    @JsonIgnoreProperties(value = "componentDetails", allowSetters = true)
    private Environment environment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public ComponentDetails server(String server) {
        this.server = server;
        return this;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public ComponentDetails port(String port) {
        this.port = port;
        return this;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public ComponentDetails url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public ComponentDetails environment(Environment environment) {
        this.environment = environment;
        return this;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ArchitectureComponent getComponent() {
        return component;
    }

    public ComponentDetails component(ArchitectureComponent architectureComponent) {
        this.component = architectureComponent;
        return this;
    }

    public void setComponent(ArchitectureComponent architectureComponent) {
        this.component = architectureComponent;
    }

    public ArchitectureComponent getArchitectureComponent() {
        return architectureComponent;
    }

    public ComponentDetails architectureComponent(ArchitectureComponent architectureComponent) {
        this.architectureComponent = architectureComponent;
        return this;
    }

    public void setArchitectureComponent(ArchitectureComponent architectureComponent) {
        this.architectureComponent = architectureComponent;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public ComponentDetails environment(Environment environment) {
        this.environment = environment;
        return this;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComponentDetails)) {
            return false;
        }
        return id != null && id.equals(((ComponentDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComponentDetails{" +
            "id=" + getId() +
            ", server='" + getServer() + "'" +
            ", port='" + getPort() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
