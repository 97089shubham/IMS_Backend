package com.licious.InventoryManagement.repository.read;

import com.licious.InventoryManagement.entity.Skus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadSkusRepository extends JpaRepository<Skus, String> {
}
