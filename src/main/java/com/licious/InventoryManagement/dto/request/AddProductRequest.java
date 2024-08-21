package com.licious.InventoryManagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * Data Transfer Object (DTO) class for product details in an add inventory request.
 * This class encapsulates the details of a product to be added to the inventory, including
 * SKU ID, SKU name, unit of measure (UOM), and quantity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProductRequest {
    @NotBlank(message = "SKU ID cannot be null")
    private String skuId;
    @NotBlank(message = "SKU Name cannot be null")
    private String skuName;
    @NotBlank(message = "UOM cannot be null")
    private String uom;
    @Positive(message = "Quantity must be positive")
    private int quantity;
}

