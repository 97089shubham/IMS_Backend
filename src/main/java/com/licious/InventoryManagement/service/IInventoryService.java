package com.licious.InventoryManagement.service;

import com.licious.InventoryManagement.dto.request.AddRequest;
import com.licious.InventoryManagement.dto.request.DeductRequest;
import com.licious.InventoryManagement.dto.response.*;
import com.licious.InventoryManagement.exception.AddInventoryException;
import com.licious.InventoryManagement.exception.InventoryDeductionException;

public interface IInventoryService {

    // Method to get the list of products for a given city
    Response getProductsList(int cityId);

    // Method to get the list of locations
    Response getLocations();

    // Method to get the list of audits
    Response getAudits();

    // Method to get the list of all skus
    Response getAllSkus();

    // Method to get the list of all inventory
    Response getAllInventory();

    // Method to add inventory based on the request data for a given city
    Response addInventory(int cityId, int clientId, AddRequest requestDto) throws AddInventoryException;

    // Method to deduct inventory based on the request data for a given city
    Response deductInventory(int cityId, int clientId, DeductRequest requestDto) throws InventoryDeductionException;
}
