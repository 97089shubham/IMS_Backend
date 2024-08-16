package com.licious.InventoryManagement.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkusResponse {
    private String skuName;
    private String skuId;
    private String uom;
}
