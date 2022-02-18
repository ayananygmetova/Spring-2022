package com.example.lab2.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PlantInventoryItem {
    @Id
    @GeneratedValue
    Long id;
    String serialNumber;

    @Enumerated(EnumType.STRING)
    EquipmentCondition equipmentCondition;
    @ManyToOne
    PlantInventoryEntry plantInfo;
}
