package com.licious.InventoryManagement.exceptions;

import com.licious.InventoryManagement.dto.error.ErrorAddResponse;
import com.licious.InventoryManagement.dto.error.ErrorDeductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * This class is responsible for handling exceptions thrown by the controllers
 * and providing a uniform response structure for error reporting.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link InventoryDeductionException} thrown by the application.
     * This method builds and returns a response indicating that inventory deduction failed
     * due to excess requested quantity or missing products.
     *
     * @param ex the InventoryDeductionException that was thrown
     * @return a ResponseEntity containing the error details and an HTTP status of BAD_REQUEST
     */
    @ExceptionHandler(InventoryDeductionException.class)
    public ResponseEntity<ErrorDeductResponse> handleInventoryDeductionException(InventoryDeductionException ex) {
        // Create the error response object with specific messages
        StringBuilder message = new StringBuilder("Inventory deduction failed due to: ");

//        if (!ex.getExcessQuantityErrors().isEmpty()) {
//            message.append("excess requested quantity for SKUs: ");
//        }
//
//        if (!ex.getMissingProductErrors().isEmpty()) {
//            message.append("missing products with SKUs: ");
//        }

        // Build the ErrorDeductResponse object with the message and error details
        ErrorDeductResponse response = ErrorDeductResponse.builder()
                .message(message.toString())
                .skusListExcess(ex.getExcessQuantityErrors())
                .skusListMissing(ex.getMissingProductErrors())
                .build();

        // Return the response with HTTP status BAD_REQUEST
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handles {@link AddInventoryException} thrown by the application.
     * This method builds and returns a response indicating that inventory addition failed
     * due to missing SKUs.
     *
     * @param ex the AddInventoryException that was thrown
     * @return a ResponseEntity containing the error details and an HTTP status of BAD_REQUEST
     */
    @ExceptionHandler(AddInventoryException.class)
    public ResponseEntity<ErrorAddResponse> handleAddInventoryException(AddInventoryException ex) {
        // Create the error response object with specific messages
        StringBuilder message = new StringBuilder("Inventory addition failed due to: ");

        if (!ex.getSkusListMissing().isEmpty()) {
            message.append("Missing SKUs: ");
        }

        // Build the ErrorAddResponse object with the message and error details
        ErrorAddResponse response = ErrorAddResponse.builder()
                .message(message.toString())
                .skusListMissing(ex.getSkusListMissing())
                .build();

        // Return the response with HTTP status BAD_REQUEST
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

}
