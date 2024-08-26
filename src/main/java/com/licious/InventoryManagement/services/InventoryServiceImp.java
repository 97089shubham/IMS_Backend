package com.licious.InventoryManagement.services;

import com.licious.InventoryManagement.dao.InventoryAuditDao;
import com.licious.InventoryManagement.dao.InventoryDao;
import com.licious.InventoryManagement.dao.LocationDao;
import com.licious.InventoryManagement.dao.SkusDao;
import com.licious.InventoryManagement.dto.request.*;
import com.licious.InventoryManagement.dto.response.*;
import com.licious.InventoryManagement.entity.Inventory;
import com.licious.InventoryManagement.exceptions.AddInventoryException;
import com.licious.InventoryManagement.exceptions.InventoryDeductionException;
import com.licious.InventoryManagement.utils.Audit;
import com.licious.InventoryManagement.utils.CreateResponse;
import com.licious.InventoryManagement.validations.AddValidate;
import com.licious.InventoryManagement.validations.DeductValidate;
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

    private final CreateResponse createResponse;

    // Method to get the list of products available in a specific city
    public Response getProductsList(int cityId) {
        List<ProductResponse> products = inventoryDao.getProductsList(cityId);
        Response response = createResponse.create("success", Optional.ofNullable(products));
        return response;
    }

    @Override
    // Method to retrieve all locations
    public Response getLocations() {
        List<LocationResponse> locations = locationDao.getLocations();
        Response response = createResponse.create("success", Optional.ofNullable(locations));
        return response;
    }

    @Override
    // Method to get all inventory audit records
    public Response getAudits() {
        List<AuditResponse> audits = inventoryAuditDao.getAudits();
        Response response = createResponse.create("success", Optional.ofNullable(audits));
        return response;
    }

    // Method to get all SKUs (Stock Keeping Units)
    public Response getAllSkus() {
        List<SkusResponse> skus = skusDao.getAllSkus();
        Response response = createResponse.create("success", Optional.ofNullable(skus));
        return response;
    }

    // Method to get all inventory records
    public Response getAllInventory() {
        System.out.println("Hello 2"); // Debugging line; should be removed in production
        List<InventoryResponse> inventories = inventoryDao.getAllInventory();
        Response response = createResponse.create("success", Optional.ofNullable(inventories));
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

        Response response = createResponse.create("Products added successfully", Optional.of(requestDto));
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

        Response response = createResponse.create("Products deducted successfully", Optional.of(requestDto));
        return response;
    }
}