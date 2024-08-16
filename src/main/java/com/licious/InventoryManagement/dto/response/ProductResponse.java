package com.licious.InventoryManagement.dto.response;

import lombok.*;

/**
 * Data Transfer Object (DTO) class for product response details.
 * This class encapsulates the details of a product that is returned in response to a request,
 * including SKU name, SKU ID, unit of measure (UOM), and quantity.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String skuName;
    private String skuId;
    private String uom;
    private int quantity;
}
