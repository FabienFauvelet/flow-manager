package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ComponentCategory} entity.
 */
public class ComponentCategoryDTO implements Serializable {
    
    private Long id;

    private String shortLabel;

    private String label;

    private String longLabel;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortLabel() {
        return shortLabel;
    }

    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLongLabel() {
        return longLabel;
    }

    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComponentCategoryDTO)) {
            return false;
        }

        return id != null && id.equals(((ComponentCategoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComponentCategoryDTO{" +
            "id=" + getId() +
            ", shortLabel='" + getShortLabel() + "'" +
            ", label='" + getLabel() + "'" +
            ", longLabel='" + getLongLabel() + "'" +
            "}";
    }
}
