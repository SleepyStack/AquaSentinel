package com.infinityloop.aquasentinel.dto;

import com.infinityloop.aquasentinel.entities.HazardReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HazardReportResponse {
    private HazardReport hazardReport;
    private int alertCounter;
    private String displayMessage;
    private String alertLevel;
}