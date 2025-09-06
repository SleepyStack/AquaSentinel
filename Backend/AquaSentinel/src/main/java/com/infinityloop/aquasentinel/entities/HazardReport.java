package com.infinityloop.aquasentinel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hazard_reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HazardReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String geoTag;
    private String hazardType;
    private String description;
    private String mediaUrl;
    private String timestamp;
    private String severityLevel;
    private String status;
    private int reportedById;
}
