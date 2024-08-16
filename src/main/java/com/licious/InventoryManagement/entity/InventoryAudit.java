package com.licious.InventoryManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Entity class representing an inventory audit record.
 * This class maps to the "inventory_audit" table in the database and is used to track changes to inventory records.
 */

@Component
@Scope("prototype")
@Entity
@Getter
@Setter
@Table(name = "inventory_audit")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int auditId;

    @Column(name = "sku_id")
    private String skuId;

    @Column(name = "city_id")
    private int cityId;

    @ManyToOne
    @JoinColumn(name = "sku_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Skus skus;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Location location;

    @Column(name = "client_id")
    private int clientId;

    @Column(name = "reference_id")
    private int referenceId;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "requested_quantity")
    private int requestedQuantity;

    @Column(name = "previous_quantity")
    private int previousQuantity;

    @Column(name = "updated_quantity")
    private int updatedQuantity;

    @Column(name = "timestamp")
    private Timestamp timeStamp;

//    @Column(name = "timestamp", updatable = false)
//    private Timestamp timeStamp;

//    @PrePersist
//    protected void onCreate() {
//        this.timeStamp = new Timestamp(System.currentTimeMillis());
//    }
}
