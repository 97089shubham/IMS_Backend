package com.licious.InventoryManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditResponse {
    private int auditId;
    private String skuId;
    private int cityId;
    private int clientId;
    private int referenceId;
    private String operationType;
    private int requestedQuantity;
    private int previousQuantity;
    private int updatedQuantity;
    private Timestamp timeStamp;
}
