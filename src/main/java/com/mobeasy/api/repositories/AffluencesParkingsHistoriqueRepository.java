package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.AffluencesParkingsHistorique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface AffluencesParkingsHistoriqueRepository extends JpaRepository<AffluencesParkingsHistorique, Short> {

    // Trouver l'historique des affluences d'un parking donné
    List<AffluencesParkingsHistorique> findByParkingId(Short parkingId);

    // Trouver l'historique des affluences après une certaine date
    List<AffluencesParkingsHistorique> findByTimestampAfter(ZonedDateTime timestamp);
}
