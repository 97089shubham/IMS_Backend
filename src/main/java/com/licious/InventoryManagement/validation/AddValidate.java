package com.licious.InventoryManagement.validation;

import com.licious.InventoryManagement.dao.SkusDao;
import com.licious.InventoryManagement.dto.request.AddProductRequest;
import com.licious.InventoryManagement.dto.request.AddRequest;
import com.licious.InventoryManagement.dto.response.SkusResponse;
import com.licious.InventoryManagement.exception.AddInventoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddValidate {

    @Autowired
    private SkusDao skusDao;

    // Method to check if SKU ID is valid
    private boolean isSkuIdValid(String skuId) {
        // Fetch all SKUs from the DAO
        List<SkusResponse> skus = skusDao.getAllSkus();

        // Check if the SKU ID is in the list of valid SKUs
        return skus.stream()
                .anyMatch(sku -> sku.getSkuId().equals(skuId));
    }

    public void validate(AddRequest requestDto) throws AddInventoryException {
        List<AddProductRequest> products = requestDto.getProducts();

        // List to hold invalid SKU IDs
        List<String> invalidSkuIds = new ArrayList<>();

        for (AddProductRequest productDto : products) {
            String skuId = productDto.getSkuId();

            // Check if the SKU ID exists in the product table
            boolean isValidSku = isSkuIdValid(skuId);
            if (!isValidSku) {
                // Add invalid SKU ID to the list
                invalidSkuIds.add(skuId);
            }
        }

        // If there are any invalid SKU IDs, throw an error
        if (!invalidSkuIds.isEmpty()) {
            throw new AddInventoryException(invalidSkuIds);
        }
    }
}
