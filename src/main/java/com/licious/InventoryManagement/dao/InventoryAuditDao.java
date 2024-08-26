package com.licious.InventoryManagement.dao;

import com.licious.InventoryManagement.dto.response.AuditResponse;
import com.licious.InventoryManagement.entity.InventoryAudit;
//import com.licious.InventoryManagement.repository.InventoryAuditRepository;
import com.licious.InventoryManagement.repository.read.ReadInventoryAuditRepository;
import com.licious.InventoryManagement.repository.write.WriteInventoryAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Access Object (DAO) class for managing inventory audit records.
 * This class provides methods to interact with the InventoryAuditRepository
 * to perform CRUD operations on InventoryAudit entities.
 */
@Component
@RequiredArgsConstructor
public class InventoryAuditDao {

//    private final InventoryAuditRepository inventoryAuditRepository;

    private final ReadInventoryAuditRepository readInventoryAuditRepository;
    private final WriteInventoryAuditRepository writeInventoryAuditRepository;

    /**
     * Saves the provided InventoryAudit entity to the database.
     *
     * @param inventoryAudit the InventoryAudit entity to be saved
     */
    public void saveInventoryAudit(InventoryAudit inventoryAudit){
//        inventoryAuditRepository.save(inventoryAudit);
        writeInventoryAuditRepository.save(inventoryAudit);
    }

    /**
     * Retrieves a list of all inventory audit records and converts them to AuditResponse DTOs.
     *
     * @return a list of AuditResponse DTOs representing all inventory audit records
     */
    public List<AuditResponse> getAudits() {
        List<InventoryAudit> audits = readInventoryAuditRepository.findAll();
//        List<InventoryAudit> audits = inventoryAuditRepository.findAll();
        return audits.stream()
                .map(this::convertToAuditResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts an InventoryAudit entity to an AuditResponse DTO.
     *
     * @param audit the InventoryAudit entity to be converted
     * @return the AuditResponse DTO representing the given InventoryAudit entity
     */
    private AuditResponse convertToAuditResponseDto(InventoryAudit audit) {
        return AuditResponse.builder()
                .auditId(audit.getAuditId())
                .skuId(audit.getSkuId())
                .cityId(audit.getCityId())
                .clientId(audit.getClientId())
                .referenceId(audit.getReferenceId())
                .operationType(audit.getOperationType())
                .requestedQuantity(audit.getRequestedQuantity())
                .previousQuantity(audit.getPreviousQuantity())
                .updatedQuantity(audit.getUpdatedQuantity())
                .timeStamp(audit.getTimeStamp())
                .build();
    }
}
