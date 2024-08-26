package com.licious.InventoryManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Getter
@Setter
@Table(name = "skus")
public class Skus {

    @Id
    @Column(name = "id")
    private String skuId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "uom")
    private String uom;

}
