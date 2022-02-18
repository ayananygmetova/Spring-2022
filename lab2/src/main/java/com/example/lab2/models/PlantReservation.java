package com.example.lab2.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PlantReservation {
    @Id
    @GeneratedValue
    Long id;

    @Embedded
    BusinessPeriod schedule;

    @ManyToOne
    PurchaseOrder rental;

    @ManyToOne
    PlantInventoryItem plant;
}
