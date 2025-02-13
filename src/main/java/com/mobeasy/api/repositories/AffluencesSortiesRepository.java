package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.AffluencesSorties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AffluencesSortiesRepository extends JpaRepository<AffluencesSorties, Short> {

    Optional<AffluencesSorties> findFirstBySortieIdOrderByTimestampDesc(Short sortieId);
    List<AffluencesSorties> findBySortie_IdOrderByTimestampDesc(Short sortieId);

    // Équivalents pour le nom de la sortie
    List<AffluencesSorties> findBySortie_NameOrderByTimestampDesc(String sortieName);
}

