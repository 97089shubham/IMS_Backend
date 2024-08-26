package com.licious.InventoryManagement.dao;

import com.licious.InventoryManagement.dto.response.InventoryResponse;
import com.licious.InventoryManagement.dto.response.ProductResponse;
import com.licious.InventoryManagement.entity.Inventory;
//import com.licious.InventoryManagement.repository.InventoryRepository;
import com.licious.InventoryManagement.repository.read.ReadInventoryRepository;
import com.licious.InventoryManagement.repository.write.WriteInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Access Object (DAO) class for managing inventory records.
 * This class provides methods to interact with the InventoryRepository
 * to perform CRUD operations on Inventory entities.
 */
@Component
@RequiredArgsConstructor
public class InventoryDao {

//    private final InventoryRepository inventoryRepository;

    private final ReadInventoryRepository readInventoryRepository;
    private final WriteInventoryRepository writeInventoryRepository;

    /**
     * Saves the provided Inventory entity to the database.
     *
     * @param inventory the Inventory entity to be saved
     */
    public void saveInventory(Inventory inventory){
        writeInventoryRepository.save(inventory);
//        inventoryRepository.save(inventory);
    }

    /**
     * Finds an Inventory entity by its SKU ID and city ID.
     *
     * @param skuId the SKU ID of the inventory item
     * @param cityId the city ID where the inventory is located
     * @return the Inventory entity matching the given SKU ID and city ID
     */
    public Inventory findBySkuIdAndCityId(String skuId, int cityId) {
//        return inventoryRepository.findBySkuIdAndCityId(skuId, cityId);
        return readInventoryRepository.findBySkuIdAndCityId(skuId, cityId);
    }

    /**
     * Gets the quantity of a specific inventory item by its SKU ID and city ID.
     *
     * @param skuId the SKU ID of the inventory item
     * @param cityId the city ID where the inventory is located
     * @return the quantity of the inventory item
     */
    public int getQuantity(String skuId, int cityId){
//        return inventoryRepository.findQuantityBySkuIdAndCityId(skuId, cityId);
        return readInventoryRepository.findQuantityBySkuIdAndCityId(skuId, cityId);
    }

    /**
     * Retrieves a list of product response DTOs for a specific city.
     *
     * @param cityId the city ID for which to retrieve the product list
     * @return a list of ProductResponseDto objects for the given city ID
     */
    public List<ProductResponse> getProductsList(int cityId){
//        return inventoryRepository.findProductsByCityId(cityId);
        return readInventoryRepository.findProductsByCityId(cityId);
    }

    /**
     * Retrieves a list of all inventory records and converts them to InventoryResponse DTOs.
     *
     * @return a list of InventoryResponse DTOs representing all inventory records
     */
    public List<InventoryResponse> getAllInventory() {
//        List<Inventory> inventories = inventoryRepository.findAll();
        List<Inventory> inventories = readInventoryRepository.findAll();
        return inventories.stream()
                .map(this::convertToInventoryResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts an Inventory entity to an InventoryResponse DTO.
     *
     * @param inventory the Inventory entity to be converted
     * @return the InventoryResponse DTO representing the given Inventory entity
     */
    private InventoryResponse convertToInventoryResponseDto(Inventory inventory) {
        return InventoryResponse.builder()
                .cityId(inventory.getCityId())
                .skuId(inventory.getSkuId())
                .quantity(inventory.getQuantity())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }
}
