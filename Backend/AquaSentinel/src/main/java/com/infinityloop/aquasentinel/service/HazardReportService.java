package com.infinityloop.aquasentinel.service;

import com.infinityloop.aquasentinel.entities.HazardReport;
import com.infinityloop.aquasentinel.entities.LocationAlert;
import com.infinityloop.aquasentinel.entities.User;
import com.infinityloop.aquasentinel.enums.ReportStatus;
import com.infinityloop.aquasentinel.repositories.LocationAlertRepository;
import com.infinityloop.aquasentinel.repositories.ReportRepository;
import com.infinityloop.aquasentinel.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.MissingResourceException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HazardReportService {

    private final ReportRepository  reportRepository;
    private final UserRepository userRepository;
    private final LocationAlertRepository  locationAlertRepository;

    public HazardReport createReport(HazardReport report) {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        report.setReportTime(LocalDateTime.now());
        User u = userRepository.findByEmail(auth.getName())
                        .orElseThrow(() -> new MissingResourceException("Username not found.", "User", auth.getName()));
        report.setStatus(ReportStatus.VERIFIED);
        report.setReportedById(u.getUser_id());
        String name = report.getGeoTag();
        LocationAlert locationAlert = locationAlertRepository.findByLocationName(name)
                .orElseThrow(() -> new MissingResourceException("Location not found.", "Location", name));

        locationAlert.setAlertCounter(locationAlert.getAlertCounter() + 1);

        String alertLevel = "NONE";
        String displayMessage = "";

        int threshold = 0;
        if (locationAlert.getAlertCounter() > threshold) {
            alertLevel = "WARNING";
            displayMessage = String.format("%s WARNING in %s, SEVERITY : %s",report.getHazardType(), name, report.getSeverityLevel());
        }
        locationAlert.setAlertLevel(alertLevel);
        locationAlertRepository.save(locationAlert);


        return reportRepository.save(report);
    }

    public Optional<HazardReport> getReport(Integer Id) {
        return reportRepository.findById(Id);
    }
}
