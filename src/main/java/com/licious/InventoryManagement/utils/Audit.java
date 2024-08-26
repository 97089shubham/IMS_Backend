package com.licious.InventoryManagement.utils;

import com.licious.InventoryManagement.dao.InventoryAuditDao;
import com.licious.InventoryManagement.dao.InventoryDao;
import com.licious.InventoryManagement.dto.request.AddProductRequest;
import com.licious.InventoryManagement.dto.request.AddRequest;
import com.licious.InventoryManagement.dto.request.DeductProductRequest;
import com.licious.InventoryManagement.dto.request.DeductRequest;
import com.licious.InventoryManagement.entity.Inventory;
import com.licious.InventoryManagement.entity.InventoryAudit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

import static com.licious.InventoryManagement.constants.StringConstants.OPERATION_TYPE_ADD;
import static com.licious.InventoryManagement.constants.StringConstants.OPERATION_TYPE_DEDUCT;

/**
 * Utility class for auditing inventory operations.
 * This class handles the creation of inventory audit records
 * for both addition and deduction of inventory.
 */
@Component
@RequiredArgsConstructor
public class Audit {

    private final InventoryDao inventoryDao;
    private final InventoryAuditDao inventoryAuditDao;

    /**
     * Audits the addition of inventory items.
     * This method creates audit records for each item in the provided AddRequest.
     * It checks if the inventory record exists and updates the audit record accordingly.
     *
     * @param cityId the ID of the city where the inventory is being added
     * @param clientId the ID of the client making the addition
     * @param requestDto the request DTO containing details of the products to be added
     * @param time the timestamp of the addition operation
     */
    public void addInventoryAudit(int cityId, int clientId, AddRequest requestDto, Timestamp time) {

        List<AddProductRequest> products = requestDto.getProducts();
        int referenceId = requestDto.getReferenceId();

        for (AddProductRequest productDto : products) {
            String skuId = productDto.getSkuId();
            int requestedQuantity = Math.abs(productDto.getQuantity());

            // Check if inventory record already exists
            Inventory existingInventory = inventoryDao.findBySkuIdAndCityId(skuId, cityId);

            int previousQuantity = 0;
            if (existingInventory != null) {
                previousQuantity = inventoryDao.getQuantity(skuId, cityId);
            }

            // Create an InventoryAudit record for the addition
            InventoryAudit inventoryAudit = InventoryAudit.builder()
                    .skuId(skuId)
                    .cityId(cityId)
                    .clientId(clientId)
                    .referenceId(referenceId)
                    .operationType(OPERATION_TYPE_ADD)
                    .requestedQuantity(requestedQuantity)
                    .previousQuantity(previousQuantity)
                    .updatedQuantity(previousQuantity + requestedQuantity)
                    .timeStamp(time)
                    .build();

            // Save the audit record to the database
            inventoryAuditDao.saveInventoryAudit(inventoryAudit);
        }
    }

    /**
     * Audits the deduction of inventory items.
     * This method creates audit records for each item in the provided DeductRequest.
     * It checks if the inventory record exists, validates the deduction,
     * and updates the audit record accordingly.
     *
     * @param cityId the ID of the city where the inventory is being deducted
     * @param clientId the ID of the client making the deduction
     * @param requestDto the request DTO containing details of the products to be deducted
     * @param time the timestamp of the deduction operation
     */
    public void deductInventoryAudit(int cityId, int clientId, DeductRequest requestDto, Timestamp time) {
        List<DeductProductRequest> products = requestDto.getProducts();
        int referenceId = requestDto.getReferenceId();

        for (DeductProductRequest productDto : products) {

            String skuId = productDto.getSkuId();
            int requestedQuantity = Math.abs(productDto.getQuantity());

            // Check if inventory record already exists
            Inventory existingInventory = inventoryDao.findBySkuIdAndCityId(skuId, cityId);
            if (existingInventory != null) {
                int previousQuantity = inventoryDao.getQuantity(skuId, cityId);
                int toBeUpdatedQuantity = previousQuantity - requestedQuantity;

                // Only create an audit record if the deduction does not exceed the current quantity
                if (toBeUpdatedQuantity >= 0) {
                    InventoryAudit inventoryAudit = InventoryAudit.builder()
                            .skuId(skuId)
                            .cityId(cityId)
                            .clientId(clientId)
                            .referenceId(referenceId)
                            .operationType(OPERATION_TYPE_DEDUCT)
                            .requestedQuantity(requestedQuantity)
                            .previousQuantity(previousQuantity)
                            .updatedQuantity(toBeUpdatedQuantity)
                            .timeStamp(time)
                            .build();

                    // Save the audit record to the database
                    inventoryAuditDao.saveInventoryAudit(inventoryAudit);
                }
            }
        }
    }
}
