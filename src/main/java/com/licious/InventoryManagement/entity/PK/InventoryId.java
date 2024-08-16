package com.licious.InventoryManagement.entity.PK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key class for the Inventory entity.
 * This class is used to represent a composite key consisting of SKU ID and city ID.
 * It implements Serializable and overrides equals and hashCode methods for proper comparison
 * and hashing in collections and persistence contexts.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryId implements Serializable {
    private String skuId;
    private int cityId;

    // Override equals to compare InventoryId objects based on skuId and cityId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryId that = (InventoryId) o;
        return cityId == that.cityId && Objects.equals(skuId, that.skuId);
    }

    // Override hashCode to ensure consistent hashing based on skuId and cityId
    @Override
    public int hashCode() {
        return Objects.hash(skuId, cityId);
    }
}
