package com.licious.InventoryManagement.repository;

import com.licious.InventoryManagement.entity.Skus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkusRepository extends JpaRepository<Skus, String> {
}
