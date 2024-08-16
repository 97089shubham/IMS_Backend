package com.licious.InventoryManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private String skuId;
    private int cityId;
    private int quantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
