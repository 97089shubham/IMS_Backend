package com.licious.InventoryManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) class for the add request.
 * This class encapsulates the details required to add products to inventory,
 * including a reference ID and a list of products to be added.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRequest {
    private int referenceId;
    private List<AddProductRequest> products;
}
