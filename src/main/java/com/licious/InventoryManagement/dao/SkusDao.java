package com.licious.InventoryManagement.dao;

import com.licious.InventoryManagement.dto.response.SkusResponse;
import com.licious.InventoryManagement.entity.Skus;
//import com.licious.InventoryManagement.repository.SkusRepository;
import com.licious.InventoryManagement.repository.read.ReadSkusRepository;
import com.licious.InventoryManagement.repository.write.WriteSkusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Access Object (DAO) class for managing SKU records.
 * This class provides methods to interact with the SkusRepository
 * to perform CRUD operations on Skus entities.
 */
@Component
@RequiredArgsConstructor
public class SkusDao {

//    private final SkusRepository skusRepository;

    private final ReadSkusRepository readSkusRepository;
    private final WriteSkusRepository writeSkusRepository;

    /**
     * Retrieves a list of all SKUs and converts them to SkusResponse DTOs.
     *
     * @return a list of SkusResponse DTOs representing all SKU records
     */
    public List<SkusResponse> getAllSkus() {
//        List<Skus> skus = skusRepository.findAll();
        List<Skus> skus = readSkusRepository.findAll();
        return skus.stream()
                .map(this::convertToSkuResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a Skus entity to a SkusResponse DTO.
     *
     * @param skus the Skus entity to be converted
     * @return the SkusResponse DTO representing the given Skus entity
     */
    private SkusResponse convertToSkuResponseDto(Skus skus) {
        return SkusResponse.builder()
                .skuId(skus.getSkuId())
                .skuName(skus.getSku())
                .uom(skus.getUom())
                .build();
    }
}
