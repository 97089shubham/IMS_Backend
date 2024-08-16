package com.licious.InventoryManagement.dao;

import com.licious.InventoryManagement.dto.response.LocationResponse;
import com.licious.InventoryManagement.entity.Location;
import com.licious.InventoryManagement.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Access Object (DAO) class for managing location records.
 * This class provides methods to interact with the LocationRepository
 * to perform CRUD operations on Location entities.
 */
@Component
@RequiredArgsConstructor
public class LocationDao {

    private final LocationRepository locationRepository;

    /**
     * Retrieves a list of all locations and converts them to LocationResponse DTOs.
     *
     * @return a list of LocationResponse DTOs representing all location records
     */
    public List<LocationResponse> getLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(this::convertToLocationResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a Location entity to a LocationResponse DTO.
     *
     * @param location the Location entity to be converted
     * @return the LocationResponse DTO representing the given Location entity
     */
    private LocationResponse convertToLocationResponseDto(Location location) {
        return LocationResponse.builder()
                .cityID(location.getCityId())
                .cityName(location.getCityName())
                .state(location.getState())
                .build();
    }
}
