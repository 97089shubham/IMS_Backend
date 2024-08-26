package com.licious.InventoryManagement.repository.write;

import com.licious.InventoryManagement.entity.Inventory;
import com.licious.InventoryManagement.entity.PK.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteInventoryRepository extends JpaRepository<Inventory, InventoryId> {
}
