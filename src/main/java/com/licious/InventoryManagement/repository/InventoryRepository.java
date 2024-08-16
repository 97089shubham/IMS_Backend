package com.licious.InventoryManagement.repository;

import com.licious.InventoryManagement.dto.response.ProductResponse;
import com.licious.InventoryManagement.entity.Inventory;
import com.licious.InventoryManagement.entity.PK.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on the Inventory entity.
 * Extends JpaRepository to provide standard database operations.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {

    /**
     * Finds products for a specific city and returns them as a list of ProductResponseDto.
     * Uses a custom JPQL query to join Inventory and Skus entities.
     *
     * @param cityId the ID of the city to find products for
     * @return a list of ProductResponseDto containing product details
     */
    @Query("SELECT new com.licious.InventoryManagement.dto.response.ProductResponse(s.sku, i.skuId, s.uom, i.quantity) " +
            "FROM Inventory i JOIN Skus s ON i.skuId = s.skuId " +
            "WHERE i.cityId = :cityId")
    List<ProductResponse> findProductsByCityId(@Param("cityId") int cityId);

    /**
     * Finds an Inventory record by SKU ID and city ID.
     *
     * @param skuId the SKU ID of the product
     * @param cityId the ID of the city
     * @return the Inventory entity matching the SKU ID and city ID, or null if not found
     */
    @Query("SELECT i FROM Inventory i WHERE i.skuId = :skuId AND i.cityId = :cityId")
    Inventory findBySkuIdAndCityId(@Param("skuId") String skuId, @Param("cityId") int cityId);

    /**
     * Finds the quantity of a product by SKU ID and city ID.
     *
     * @param skuId the SKU ID of the product
     * @param cityId the ID of the city
     * @return the quantity of the product, or null if not found
     */
    @Query("SELECT i.quantity FROM Inventory i WHERE i.skuId = :skuId AND i.cityId = :cityId")
    Integer findQuantityBySkuIdAndCityId(@Param("skuId") String skuId, @Param("cityId") int cityId);
}
