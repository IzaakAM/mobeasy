package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.AffluencesParkings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AffluencesParkingsRepository extends JpaRepository<AffluencesParkings, Short> {

    List<AffluencesParkings> findByParkingId(Short parkingId);

    // Supprimer ou commenter la méthode personnalisée existante :
    // @Query("SELECT a FROM AffluencesParkings a WHERE a.parking.id = :parkingId ORDER BY a.timestamp DESC")
    // Optional<AffluencesParkings> findTopByParkingIdOrderByTimestampDesc(@Param("parkingId") Short parkingId);

    // Ajouter la méthode dérivée pour récupérer la dernière affluence
    Optional<AffluencesParkings> findFirstByParkingIdOrderByTimestampDesc(Short parkingId);
}

