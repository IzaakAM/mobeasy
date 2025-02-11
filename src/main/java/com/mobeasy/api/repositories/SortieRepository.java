package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface SortieRepository extends JpaRepository<Sortie, Short> {

    // Trouver une sortie par son nom
    Sortie findByName(String name);

    // Trouver les sorties créées après une certaine date
    List<Sortie> findByDateCreationAfter(ZonedDateTime date);
}
