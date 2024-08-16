package com.licious.InventoryManagement.dto.request;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) class for deducting products from inventory.
 * This class encapsulates the details required for a deduction request, including
 * a reference ID and a list of products to be deducted.
 */
@Data
public class DeductRequest {
    private int referenceId;
    private List<DeductProductRequest> products;
}
