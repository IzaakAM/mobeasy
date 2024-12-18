package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.AffluencesParkings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface AffluencesParkingsRepository extends JpaRepository<AffluencesParkings, Short> {

    // Trouver les affluences d'un parking donné
    List<AffluencesParkings> findByParkingId(Short parkingId);

    // Trouver les affluences après une certaine date
    List<AffluencesParkings> findByTimestampAfter(ZonedDateTime timestamp);
}
