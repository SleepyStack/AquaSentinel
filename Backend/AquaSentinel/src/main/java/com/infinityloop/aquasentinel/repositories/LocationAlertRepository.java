package com.infinityloop.aquasentinel.repositories;

import com.infinityloop.aquasentinel.entities.LocationAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationAlertRepository extends JpaRepository<LocationAlert, Integer> {
    Optional<LocationAlert> findByLocationName(String locationName);
}