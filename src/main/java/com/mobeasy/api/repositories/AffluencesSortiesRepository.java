package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.AffluencesSorties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface AffluencesSortiesRepository extends JpaRepository<AffluencesSorties, Short> {

    // Trouver les affluences d'une sortie donnée
    List<AffluencesSorties> findBySortieId(Short sortieId);

    // Trouver les affluences après une certaine date
    List<AffluencesSorties> findByTimestampAfter(ZonedDateTime timestamp);
}
