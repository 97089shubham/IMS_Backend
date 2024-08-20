package com.licious.InventoryManagement.service;

import com.licious.InventoryManagement.dao.InventoryAuditDao;
import com.licious.InventoryManagement.dao.InventoryDao;
import com.licious.InventoryManagement.dao.LocationDao;
import com.licious.InventoryManagement.dao.SkusDao;
import com.licious.InventoryManagement.dto.request.*;
import com.licious.InventoryManagement.dto.response.*;
import com.licious.InventoryManagement.entity.Inventory;
import com.licious.InventoryManagement.exception.AddInventoryException;
import com.licious.InventoryManagement.exception.InventoryDeductionException;
import com.licious.InventoryManagement.util.Audit;
import com.licious.InventoryManagement.validation.AddValidate;
import com.licious.InventoryManagement.validation.DeductValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImp implements InventoryService {
    private final InventoryDao inventoryDao;
    private final InventoryAuditDao inventoryAuditDao;
    private final LocationDao locationDao;
    private final SkusDao skusDao;

    private final AddValidate addValidate;
    private final DeductValidate deductValidate;
    private final Audit audit;

    // Method to get the list of products available in a specific city
    public Response getProductsList(int cityId) {
        List<ProductResponse> products = inventoryDao.getProductsList(cityId);
        Response response = Response.builder()
                .message("success")
                .data(Optional.ofNullable(products))
                .build();
        return response;
    }

    @Override
    // Method to retrieve all locations
    public Response getLocations() {
        List<LocationResponse> locations = locationDao.getLocations();
        Response response = Response.builder()
                .message("success")
                .data(Optional.ofNullable(locations))
                .build();

        return response;
    }

    @Override
    // Method to get all inventory audit records
    public Response getAudits() {
        List<AuditResponse> audits = inventoryAuditDao.getAudits();
        Response response = Response.builder()
                .message("success")
                .data(Optional.ofNullable(audits))
                .build();

        return response;
    }

    // Method to get all SKUs (Stock Keeping Units)
    public Response getAllSkus() {
        List<SkusResponse> skus = skusDao.getAllSkus();
        Response response = Response.builder()
                .message("success")
                .data(Optional.ofNullable(skus))
                .build();

        return response;
    }

    // Method to get all inventory records
    public Response getAllInventory() {
        System.out.println("Hello 2"); // Debugging line; should be removed in production
        List<InventoryResponse> inventories = inventoryDao.getAllInventory();
        Response response = Response.builder()
                .message("success")
                .data(Optional.ofNullable(inventories))
                .build();

        return response;
    }

    // Method to add inventory based on the provided request data and timestamp
    public Response addInventory(int cityId, int clientId, AddRequest requestDto) throws AddInventoryException {
        // Get the current timestamp
        Timestamp time = new Timestamp(System.currentTimeMillis());

        // Validate the request data
        addValidate.validate(requestDto);

        // Log the inventory addition action
        audit.addInventoryAudit(cityId, clientId, requestDto, time);

        List<AddProductRequest> products = requestDto.getProducts();

        // Process each product in the request
        for (AddProductRequest productDto : products) {
            String skuId = productDto.getSkuId();

            // Check if an inventory record already exists for the SKU and city
            Inventory existingInventory = inventoryDao.findBySkuIdAndCityId(skuId, cityId);

            if (existingInventory != null) {
                // Update the existing inventory record
                int previousQuantity = existingInventory.getQuantity();
                int requestedQuantity = Math.abs(productDto.getQuantity());

                existingInventory.setQuantity(previousQuantity + requestedQuantity);
                existingInventory.setUpdatedAt(time);

                inventoryDao.saveInventory(existingInventory);
            }
            else {
                // Create a new inventory record
                Inventory newInventory = Inventory.builder()
                        .skuId(skuId)
                        .cityId(cityId)
                        .quantity(productDto.getQuantity())
                        .createdAt(time)
                        .updatedAt(time)
                        .build();

                inventoryDao.saveInventory(newInventory);
            }
        }

        Response response = Response.builder()
                .message("Products added successfully")
                .data(Optional.of(requestDto))
                .build();
        return response;
    }

    // Method to deduct inventory based on the provided request data and timestamp
    public Response deductInventory(int cityId, int clientId, DeductRequest requestDto) throws InventoryDeductionException {
        // Get the current timestamp
        Timestamp time = new Timestamp(System.currentTimeMillis());

        // Validate the request data
        deductValidate.validate(cityId, requestDto);

        // Log the inventory deduction action
        audit.deductInventoryAudit(cityId, clientId, requestDto, time);

        List<DeductProductRequest> products = requestDto.getProducts();

        // Process each product in the request
        for (DeductProductRequest productDto : products) {
            String skuId = productDto.getSkuId();

            // Check if an inventory record exists for the SKU and city
            Inventory existingInventory = inventoryDao.findBySkuIdAndCityId(skuId, cityId);

            if (existingInventory != null) {
                // Update the existing inventory record
                int previousQuantity = existingInventory.getQuantity();
                int requestedQuantity = Math.abs(productDto.getQuantity());

                int toBeUpdatedQuantity = previousQuantity - requestedQuantity;
                if (toBeUpdatedQuantity >= 0) {
                    existingInventory.setQuantity(toBeUpdatedQuantity);
                    existingInventory.setUpdatedAt(time);
                    inventoryDao.saveInventory(existingInventory);
                }
            }
        }

        Response response = Response.builder()
                .message("Products deducted successfully")
                .data(Optional.of(requestDto))
                .build();
        return response;
    }
}