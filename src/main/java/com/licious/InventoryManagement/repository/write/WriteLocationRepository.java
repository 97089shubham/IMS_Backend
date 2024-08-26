package com.licious.InventoryManagement.repository.write;

import com.licious.InventoryManagement.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteLocationRepository extends JpaRepository<Location, Integer> {
}
