package com.licious.InventoryManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Getter
@Setter
@Table(name = "location")
public class Location {

    @Id
    @Column(name = "id")
    private int cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "state")
    private String state;

}
