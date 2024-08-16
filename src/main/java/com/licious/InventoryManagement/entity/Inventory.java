package com.licious.InventoryManagement.entity;

import com.licious.InventoryManagement.entity.PK.InventoryId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.sql.Timestamp;

/**
 * Entity class representing an inventory record.
 * This class maps to the "inventory" table in the database and uses a composite primary key consisting of SKU ID and city ID.
 * It includes relationships with other entities such as Skus and Location.
 */

@Scope("prototype")
@Entity
@Getter
@Setter
@IdClass(InventoryId.class)
@Table(name = "inventory")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @Column(name = "sku_id")
    private String skuId;

    @Id
    @Column(name = "city_id")
    private int cityId;

    @ManyToOne
    @JoinColumn(name = "sku_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Skus skus;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Location location;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
