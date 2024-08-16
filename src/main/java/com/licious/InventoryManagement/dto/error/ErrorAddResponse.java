package com.licious.InventoryManagement.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ErrorAddResponse {
    private String message; // The error message describing the deduction error
    private List<String> skusListMissing; // List of SKU IDs that is missing
}
