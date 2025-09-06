package com.infinityloop.aquasentinel.controllers;


import com.infinityloop.aquasentinel.entities.HazardReport;
import com.infinityloop.aquasentinel.service.HazardReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/ReportHazard")
public class ReportController {

    private final HazardReportService hazardReportService;

    @PostMapping
    public HazardReport createReport(@RequestBody HazardReport hazardReport) {
        return hazardReportService.createReport(hazardReport);
    }

    @GetMapping
    public Optional<HazardReport> getReport(int id) {
        return hazardReportService.getReport(id);
    }

}
