package com.licious.InventoryManagement.repository.read;

import com.licious.InventoryManagement.entity.InventoryAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadInventoryAuditRepository extends JpaRepository<InventoryAudit, Integer> {

}
