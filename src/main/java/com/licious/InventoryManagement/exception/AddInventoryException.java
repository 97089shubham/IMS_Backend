package com.licious.InventoryManagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Custom exception class for handling errors that occur
 * when adding inventory items. This exception is thrown
 * when there are issues with inventory addition, such as
 * missing SKUs.
 */
@Getter
@AllArgsConstructor
public class AddInventoryException extends Throwable {

    /**
     * List of SKU IDs that are missing or causing issues
     * during the inventory addition process.
     */
    private final List<String> skusListMissing;
}
