package com.licious.InventoryManagement.repository.write;

import com.licious.InventoryManagement.entity.InventoryAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteInventoryAuditRepository extends JpaRepository<InventoryAudit, Integer> {
}
