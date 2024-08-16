package com.licious.InventoryManagement.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) class for error responses related to inventory deductions.
 * This class encapsulates the error message and the list of SKU IDs that caused the error.
 */

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ErrorDeductResponse {
    private String message; // The error message describing the deduction error
    private List<String> skusListMissing; // List of SKU IDs that is missing
    private List<String> skusListExcess; // List of SKU IDs that caused the deduction error
}
