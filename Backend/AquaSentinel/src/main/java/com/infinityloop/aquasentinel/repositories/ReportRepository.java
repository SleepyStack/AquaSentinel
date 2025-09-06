package com.infinityloop.aquasentinel.repositories;

import com.infinityloop.aquasentinel.entities.HazardReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<HazardReport,Integer> {

    @Query("""
SELECT h FROM HazardReport h WHERE h.id = :id
            """)
    Optional<HazardReport> findById(Integer Id);
}
