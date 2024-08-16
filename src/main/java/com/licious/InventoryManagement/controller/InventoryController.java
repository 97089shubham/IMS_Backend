package com.licious.InventoryManagement.controller;

import com.licious.InventoryManagement.dto.request.*;
import com.licious.InventoryManagement.dto.response.*;
import com.licious.InventoryManagement.exception.AddInventoryException;
import com.licious.InventoryManagement.exception.InventoryDeductionException;
import com.licious.InventoryManagement.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.licious.InventoryManagement.constants.RoutesConstants.INVENTORY;

/**
 * Controller class to handle inventory-related HTTP requests.
 */
@Controller
@RestController
@RequestMapping(INVENTORY)
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;

    /**
     * Endpoint to get the list of cities.
     *
     * @return ResponseEntity with the list of cities.
     */
    @GetMapping("/cities")
    public ResponseEntity<Response> getCitiesList() {
        Response response = inventoryService.getLocations();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to get the list of inventory audits.
     *
     * @return ResponseEntity with the list of audits.
     */
    @GetMapping("/audits")
    public ResponseEntity<Response> getAudits() {
        Response response = inventoryService.getAudits();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to get all inventory items.
     *
     * @return ResponseEntity with the list of all inventory items.
     */
    @GetMapping("/inventories")
    public ResponseEntity<Response> getAllInventory() {
        Response response = inventoryService.getAllInventory();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to get all SKUs (Stock Keeping Units).
     *
     * @return ResponseEntity with the list of all SKUs.
     */
    @GetMapping("/skus")
    public ResponseEntity<Response> getAllSkus() {
        Response response = inventoryService.getAllSkus();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to get the list of products for a given city.
     *
     * @param cityId the ID of the city.
     * @param clientId the ID of the client.
     * @return ResponseEntity with the list of products for the given city.
     */
    @GetMapping("/skus/{city_id}")
    public ResponseEntity<Response> getProductsList(
            @PathVariable("city_id") int cityId,
            @RequestHeader("client_id") int clientId) {
        Response response = inventoryService.getProductsList(cityId);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to add inventory for a given city.
     *
     * @param cityId the ID of the city.
     * @param clientId the ID of the client.
     * @param body the request body containing inventory details.
     * @return ResponseEntity with the result of the inventory addition.
     * @throws AddInventoryException if there is an error adding the inventory.
     */
    @PostMapping("/add/{city_id}")
    public ResponseEntity<Response> addInventory(
            @PathVariable("city_id") int cityId,
            @RequestHeader("client_id") int clientId,
            @RequestBody AddRequest body) throws AddInventoryException {
        Response response = inventoryService.addInventory(cityId, clientId, body);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to deduct inventory for a given city.
     *
     * @param cityId the ID of the city.
     * @param clientId the ID of the client.
     * @param body the request body containing deduction details.
     * @return ResponseEntity with the result of the inventory deduction.
     * @throws InventoryDeductionException if there is an error deducting the inventory.
     */
    @PostMapping("/deduct/{city_id}")
    public ResponseEntity<Response> deductInventory(
            @PathVariable("city_id") int cityId,
            @RequestHeader("client_id") int clientId,
            @RequestBody DeductRequest body) throws InventoryDeductionException {
        Response response = inventoryService.deductInventory(cityId, clientId, body);
        return ResponseEntity.ok(response);
    }
}
