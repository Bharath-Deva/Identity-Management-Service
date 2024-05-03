package com.flyerssoft.ims.model.repository;

import com.flyerssoft.ims.model.entity.Entitlement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Entitlement table - Repository interface.
 */
public interface EntitlementRepository extends JpaRepository<Entitlement, Long> {
}
