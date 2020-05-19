package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ComponentDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ComponentDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComponentDetailsRepository extends JpaRepository<ComponentDetails, Long> {
}
