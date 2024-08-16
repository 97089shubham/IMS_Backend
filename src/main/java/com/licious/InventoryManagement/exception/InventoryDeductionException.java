package com.licious.InventoryManagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Custom exception class to handle errors related to inventory deduction.
 * This exception is thrown when there are issues with the deduction process,
 * such as attempting to deduct more inventory than is available.
 */
@Getter
@AllArgsConstructor
public class InventoryDeductionException extends Throwable {
    private final List<String> excessQuantityErrors; // SKUs where deduction exceeded available quantity
    private final List<String> missingProductErrors; // SKUs that are missing from inventory
}
