package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.AffluencesSortiesHistorique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface AffluencesSortiesHistoriqueRepository extends JpaRepository<AffluencesSortiesHistorique, Short> {

    // Trouver l'historique des affluences d'une sortie donnée
    List<AffluencesSortiesHistorique> findBySortieId(Short sortieId);

    // Trouver l'historique des affluences après une certaine date
    List<AffluencesSortiesHistorique> findByTimestampAfter(ZonedDateTime timestamp);
}
