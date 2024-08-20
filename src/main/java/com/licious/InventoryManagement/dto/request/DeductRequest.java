package com.licious.InventoryManagement.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data Transfer Object (DTO) class for deducting products from inventory.
 * This class encapsulates the details required for a deduction request, including
 * a reference ID and a list of products to be deducted.
 */
@Data
public class DeductRequest {
    @Positive(message = "Reference Id must be positive")
    private int referenceId;
    private List<@Valid DeductProductRequest> products;
}
