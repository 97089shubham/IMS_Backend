package com.licious.InventoryManagement.repository.write;

import com.licious.InventoryManagement.entity.Skus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteSkusRepository extends JpaRepository<Skus, String> {
}
