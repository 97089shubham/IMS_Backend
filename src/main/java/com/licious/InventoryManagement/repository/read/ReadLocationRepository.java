package com.licious.InventoryManagement.repository.read;

import com.licious.InventoryManagement.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadLocationRepository extends JpaRepository<Location, Integer> {
}
