package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "components")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ArchitectureComponent> architectureComponents = new HashSet<>();

    @OneToMany(mappedBy = "flows")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Flow> flows = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Project name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ArchitectureComponent> getArchitectureComponents() {
        return architectureComponents;
    }

    public Project architectureComponents(Set<ArchitectureComponent> architectureComponents) {
        this.architectureComponents = architectureComponents;
        return this;
    }

    public Project addArchitectureComponent(ArchitectureComponent architectureComponent) {
        this.architectureComponents.add(architectureComponent);
        architectureComponent.setComponents(this);
        return this;
    }

    public Project removeArchitectureComponent(ArchitectureComponent architectureComponent) {
        this.architectureComponents.remove(architectureComponent);
        architectureComponent.setComponents(null);
        return this;
    }

    public void setArchitectureComponents(Set<ArchitectureComponent> architectureComponents) {
        this.architectureComponents = architectureComponents;
    }

    public Set<Flow> getFlows() {
        return flows;
    }

    public Project flows(Set<Flow> flows) {
        this.flows = flows;
        return this;
    }

    public Project addFlow(Flow flow) {
        this.flows.add(flow);
        flow.setFlows(this);
        return this;
    }

    public Project removeFlow(Flow flow) {
        this.flows.remove(flow);
        flow.setFlows(null);
        return this;
    }

    public void setFlows(Set<Flow> flows) {
        this.flows = flows;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return id != null && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
