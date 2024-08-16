package com.licious.InventoryManagement.validation;

import com.licious.InventoryManagement.dao.InventoryDao;
import com.licious.InventoryManagement.dto.request.DeductProductRequest;
import com.licious.InventoryManagement.dto.request.DeductRequest;
import com.licious.InventoryManagement.entity.Inventory;
import com.licious.InventoryManagement.exception.InventoryDeductionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeductValidate {
    private final InventoryDao inventoryDao;

    public void validate(int cityId, DeductRequest requestDto) throws InventoryDeductionException{
        List<DeductProductRequest> products = requestDto.getProducts();

        List<String> excessQuantityErrors = new ArrayList<>();
        List<String> missingProductErrors = new ArrayList<>();

        for (DeductProductRequest productDto : products) {

            String skuId = productDto.getSkuId();
            int requestedQuantity = productDto.getQuantity();

            // Check if inventory record already exists
            Inventory existingInventory = inventoryDao.findBySkuIdAndCityId(skuId, cityId);
            if (existingInventory != null) {
                int previousQuantity = inventoryDao.getQuantity(skuId, cityId);
                int toBeUpdatedQuantity = previousQuantity - requestedQuantity;

                if (toBeUpdatedQuantity < 0) {
                    excessQuantityErrors.add(skuId);
                }
            } else {
                // Product is not found in inventory
                missingProductErrors.add(skuId);
            }
        }
        if (!excessQuantityErrors.isEmpty() || !missingProductErrors.isEmpty()) {
            // Throw a custom exception with all accumulated errors
            throw new InventoryDeductionException(excessQuantityErrors, missingProductErrors);
        }
    }
}
