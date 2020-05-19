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

    @OneToMany(mappedBy = "architectureComponent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ComponentDetails> detailsByEnvironements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "architectureComponents", allowSetters = true)
    private ComponentCategory category;

    @ManyToOne
    @JsonIgnoreProperties(value = "components", allowSetters = true)
    private Project project;

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

    public Set<ComponentDetails> getDetailsByEnvironements() {
        return detailsByEnvironements;
    }

    public ArchitectureComponent detailsByEnvironements(Set<ComponentDetails> componentDetails) {
        this.detailsByEnvironements = componentDetails;
        return this;
    }

    public ArchitectureComponent addDetailsByEnvironement(ComponentDetails componentDetails) {
        this.detailsByEnvironements.add(componentDetails);
        componentDetails.setArchitectureComponent(this);
        return this;
    }

    public ArchitectureComponent removeDetailsByEnvironement(ComponentDetails componentDetails) {
        this.detailsByEnvironements.remove(componentDetails);
        componentDetails.setArchitectureComponent(null);
        return this;
    }

    public void setDetailsByEnvironements(Set<ComponentDetails> componentDetails) {
        this.detailsByEnvironements = componentDetails;
    }

    public ComponentCategory getCategory() {
        return category;
    }

    public ArchitectureComponent category(ComponentCategory componentCategory) {
        this.category = componentCategory;
        return this;
    }

    public void setCategory(ComponentCategory componentCategory) {
        this.category = componentCategory;
    }

    public Project getProject() {
        return project;
    }

    public ArchitectureComponent project(Project project) {
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
