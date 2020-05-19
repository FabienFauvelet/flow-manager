package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ComponentCategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ComponentCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComponentCategoryRepository extends JpaRepository<ComponentCategory, Long> {
}
