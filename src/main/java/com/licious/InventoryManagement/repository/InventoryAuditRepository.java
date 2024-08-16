package com.licious.InventoryManagement.repository;

import com.licious.InventoryManagement.entity.InventoryAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the InventoryAudit entity.
 * Extends JpaRepository to provide standard database operations.
 */
@Repository
public interface InventoryAuditRepository extends JpaRepository<InventoryAudit, Integer> {

}
