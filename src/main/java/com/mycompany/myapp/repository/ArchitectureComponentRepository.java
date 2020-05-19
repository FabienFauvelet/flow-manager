package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ArchitectureComponent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ArchitectureComponent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArchitectureComponentRepository extends JpaRepository<ArchitectureComponent, Long> {
}
