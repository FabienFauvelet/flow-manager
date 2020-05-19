package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Flow.
 */
@Entity
@Table(name = "flow")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JsonIgnoreProperties(value = "flows", allowSetters = true)
    private ArchitectureComponent componentFrom;

    @ManyToOne
    @JsonIgnoreProperties(value = "flows", allowSetters = true)
    private ArchitectureComponent componentTo;

    @ManyToOne
    @JsonIgnoreProperties(value = "flows", allowSetters = true)
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Flow description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isStatus() {
        return status;
    }

    public Flow status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArchitectureComponent getComponentFrom() {
        return componentFrom;
    }

    public Flow componentFrom(ArchitectureComponent architectureComponent) {
        this.componentFrom = architectureComponent;
        return this;
    }

    public void setComponentFrom(ArchitectureComponent architectureComponent) {
        this.componentFrom = architectureComponent;
    }

    public ArchitectureComponent getComponentTo() {
        return componentTo;
    }

    public Flow componentTo(ArchitectureComponent architectureComponent) {
        this.componentTo = architectureComponent;
        return this;
    }

    public void setComponentTo(ArchitectureComponent architectureComponent) {
        this.componentTo = architectureComponent;
    }

    public Project getProject() {
        return project;
    }

    public Flow project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flow)) {
            return false;
        }
        return id != null && id.equals(((Flow) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Flow{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
