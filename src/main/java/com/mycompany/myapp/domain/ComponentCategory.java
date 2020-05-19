package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ComponentCategory.
 */
@Entity
@Table(name = "component_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ComponentCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_label")
    private String shortLabel;

    @Column(name = "label")
    private String label;

    @Column(name = "long_label")
    private String longLabel;

    @OneToMany(mappedBy = "componentCategory")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ArchitectureComponent> categories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortLabel() {
        return shortLabel;
    }

    public ComponentCategory shortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
        return this;
    }

    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    public String getLabel() {
        return label;
    }

    public ComponentCategory label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLongLabel() {
        return longLabel;
    }

    public ComponentCategory longLabel(String longLabel) {
        this.longLabel = longLabel;
        return this;
    }

    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    public Set<ArchitectureComponent> getCategories() {
        return categories;
    }

    public ComponentCategory categories(Set<ArchitectureComponent> architectureComponents) {
        this.categories = architectureComponents;
        return this;
    }

    public ComponentCategory addCategory(ArchitectureComponent architectureComponent) {
        this.categories.add(architectureComponent);
        architectureComponent.setComponentCategory(this);
        return this;
    }

    public ComponentCategory removeCategory(ArchitectureComponent architectureComponent) {
        this.categories.remove(architectureComponent);
        architectureComponent.setComponentCategory(null);
        return this;
    }

    public void setCategories(Set<ArchitectureComponent> architectureComponents) {
        this.categories = architectureComponents;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComponentCategory)) {
            return false;
        }
        return id != null && id.equals(((ComponentCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComponentCategory{" +
            "id=" + getId() +
            ", shortLabel='" + getShortLabel() + "'" +
            ", label='" + getLabel() + "'" +
            ", longLabel='" + getLongLabel() + "'" +
            "}";
    }
}
