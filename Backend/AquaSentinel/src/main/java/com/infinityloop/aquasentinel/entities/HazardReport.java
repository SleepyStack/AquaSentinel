package com.infinityloop.aquasentinel.entities;

import com.infinityloop.aquasentinel.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String description;
    private String mediaUrl;

    // This annotation is the final fix. It forces SQL that MySQL 5.5 understands.
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime reportTime;

    private String severityLevel;

    @Enumerated(EnumType.ORDINAL)
    private ReportStatus status;

    private int reportedById;
}
