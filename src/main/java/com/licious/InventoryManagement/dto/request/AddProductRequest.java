package com.licious.InventoryManagement.dto.request;

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
    private String skuId;
    private String skuName;
    private String uom;
    private int quantity;
}

