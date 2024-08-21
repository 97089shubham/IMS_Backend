package com.licious.InventoryManagement.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;


/**
 * Data Transfer Object (DTO) class for product details in a deduct inventory request.
 * This class encapsulates the details of a product to be deducted from the inventory, including
 * SKU ID and quantity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeductProductRequest {
    @NotBlank(message = "SKU ID cannot be null")
    String skuId;
    @Positive(message = "Quantity must be positive")
    int quantity;
}
