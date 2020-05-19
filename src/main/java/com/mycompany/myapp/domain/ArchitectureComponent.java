package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ArchitectureComponent.
 */
@Entity
@Table(name = "architecture_component")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArchitectureComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "detailsByEnvironement")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ComponentDetails> componentDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private ComponentCategory componentCategory;

    @ManyToOne
    @JsonIgnoreProperties(value = "architectureComponents", allowSetters = true)
    private Project components;

    @OneToMany(mappedBy = "architectureComponent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Flow> componentFroms = new HashSet<>();

    @OneToMany(mappedBy = "architectureComponent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Flow> componentTos = new HashSet<>();

    @OneToMany(mappedBy = "architectureComponent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ComponentDetails> components = new HashSet<>();

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

    public ArchitectureComponent name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ComponentDetails> getComponentDetails() {
        return componentDetails;
    }

    public ArchitectureComponent componentDetails(Set<ComponentDetails> componentDetails) {
        this.componentDetails = componentDetails;
        return this;
    }

    public ArchitectureComponent addComponentDetails(ComponentDetails componentDetails) {
        this.componentDetails.add(componentDetails);
        componentDetails.setDetailsByEnvironement(this);
        return this;
    }

    public ArchitectureComponent removeComponentDetails(ComponentDetails componentDetails) {
        this.componentDetails.remove(componentDetails);
        componentDetails.setDetailsByEnvironement(null);
        return this;
    }

    public void setComponentDetails(Set<ComponentDetails> componentDetails) {
        this.componentDetails = componentDetails;
    }

    public ComponentCategory getComponentCategory() {
        return componentCategory;
    }

    public ArchitectureComponent componentCategory(ComponentCategory componentCategory) {
        this.componentCategory = componentCategory;
        return this;
    }

    public void setComponentCategory(ComponentCategory componentCategory) {
        this.componentCategory = componentCategory;
    }

    public Project getComponents() {
        return components;
    }

    public ArchitectureComponent components(Project project) {
        this.components = project;
        return this;
    }

    public void setComponents(Project project) {
        this.components = project;
    }

    public Set<Flow> getComponentFroms() {
        return componentFroms;
    }

    public ArchitectureComponent componentFroms(Set<Flow> flows) {
        this.componentFroms = flows;
        return this;
    }

    public ArchitectureComponent addComponentFrom(Flow flow) {
        this.componentFroms.add(flow);
        flow.setArchitectureComponent(this);
        return this;
    }

    public ArchitectureComponent removeComponentFrom(Flow flow) {
        this.componentFroms.remove(flow);
        flow.setArchitectureComponent(null);
        return this;
    }

    public void setComponentFroms(Set<Flow> flows) {
        this.componentFroms = flows;
    }

    public Set<Flow> getComponentTos() {
        return componentTos;
    }

    public ArchitectureComponent componentTos(Set<Flow> flows) {
        this.componentTos = flows;
        return this;
    }

    public ArchitectureComponent addComponentTo(Flow flow) {
        this.componentTos.add(flow);
        flow.setArchitectureComponent(this);
        return this;
    }

    public ArchitectureComponent removeComponentTo(Flow flow) {
        this.componentTos.remove(flow);
        flow.setArchitectureComponent(null);
        return this;
    }

    public void setComponentTos(Set<Flow> flows) {
        this.componentTos = flows;
    }

    public Set<ComponentDetails> getComponents() {
        return components;
    }

    public ArchitectureComponent components(Set<ComponentDetails> componentDetails) {
        this.components = componentDetails;
        return this;
    }

    public ArchitectureComponent addComponent(ComponentDetails componentDetails) {
        this.components.add(componentDetails);
        componentDetails.setArchitectureComponent(this);
        return this;
    }

    public ArchitectureComponent removeComponent(ComponentDetails componentDetails) {
        this.components.remove(componentDetails);
        componentDetails.setArchitectureComponent(null);
        return this;
    }

    public void setComponents(Set<ComponentDetails> componentDetails) {
        this.components = componentDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArchitectureComponent)) {
            return false;
        }
        return id != null && id.equals(((ArchitectureComponent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArchitectureComponent{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
