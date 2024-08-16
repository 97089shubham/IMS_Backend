package com.licious.InventoryManagement.dto.response;

import lombok.*;

import java.util.Optional;

/**
 * Data Transfer Object (DTO) class for general response structure.
 * This class encapsulates a standard response format used for API responses,
 * including a message and an optional data payload.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private String message;
    private Optional<Object> data;
}