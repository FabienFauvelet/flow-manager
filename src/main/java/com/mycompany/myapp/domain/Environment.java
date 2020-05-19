package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Environment.
 */
@Entity
@Table(name = "environment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Environment implements Serializable {

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

    @OneToMany(mappedBy = "environment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ComponentDetails> componentDetails = new HashSet<>();

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

    public Environment shortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
        return this;
    }

    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    public String getLabel() {
        return label;
    }

    public Environment label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLongLabel() {
        return longLabel;
    }

    public Environment longLabel(String longLabel) {
        this.longLabel = longLabel;
        return this;
    }

    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    public Set<ComponentDetails> getComponentDetails() {
        return componentDetails;
    }

    public Environment componentDetails(Set<ComponentDetails> componentDetails) {
        this.componentDetails = componentDetails;
        return this;
    }

    public Environment addComponentDetails(ComponentDetails componentDetails) {
        this.componentDetails.add(componentDetails);
        componentDetails.setEnvironment(this);
        return this;
    }

    public Environment removeComponentDetails(ComponentDetails componentDetails) {
        this.componentDetails.remove(componentDetails);
        componentDetails.setEnvironment(null);
        return this;
    }

    public void setComponentDetails(Set<ComponentDetails> componentDetails) {
        this.componentDetails = componentDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Environment)) {
            return false;
        }
        return id != null && id.equals(((Environment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Environment{" +
            "id=" + getId() +
            ", shortLabel='" + getShortLabel() + "'" +
            ", label='" + getLabel() + "'" +
            ", longLabel='" + getLongLabel() + "'" +
            "}";
    }
}
