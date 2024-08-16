package com.licious.InventoryManagement.repository;

import com.licious.InventoryManagement.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
