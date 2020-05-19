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

    @OneToMany(mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ArchitectureComponent> components = new HashSet<>();

    @OneToMany(mappedBy = "project")
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

    public Set<ArchitectureComponent> getComponents() {
        return components;
    }

    public Project components(Set<ArchitectureComponent> architectureComponents) {
        this.components = architectureComponents;
        return this;
    }

    public Project addComponents(ArchitectureComponent architectureComponent) {
        this.components.add(architectureComponent);
        architectureComponent.setProject(this);
        return this;
    }

    public Project removeComponents(ArchitectureComponent architectureComponent) {
        this.components.remove(architectureComponent);
        architectureComponent.setProject(null);
        return this;
    }

    public void setComponents(Set<ArchitectureComponent> architectureComponents) {
        this.components = architectureComponents;
    }

    public Set<Flow> getFlows() {
        return flows;
    }

    public Project flows(Set<Flow> flows) {
        this.flows = flows;
        return this;
    }

    public Project addFlows(Flow flow) {
        this.flows.add(flow);
        flow.setProject(this);
        return this;
    }

    public Project removeFlows(Flow flow) {
        this.flows.remove(flow);
        flow.setProject(null);
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
