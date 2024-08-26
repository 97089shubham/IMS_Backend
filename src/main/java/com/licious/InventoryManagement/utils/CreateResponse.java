package com.licious.InventoryManagement.utils;

import com.licious.InventoryManagement.dto.response.Response;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateResponse {
    public Response create(String message, Optional<Object> data){
        Response response = Response.builder()
                .message("success")
                .data(Optional.ofNullable(data))
                .build();
        return response;
    }
}
