package com.mobeasy.api.services;

import com.mobeasy.api.entities.Sortie;
import com.mobeasy.api.repositories.SortieRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class SortieService {

    private final SortieRepository sortieRepository;

    public SortieService(SortieRepository sortieRepository) {
        this.sortieRepository = sortieRepository;
    }

    // 1) CRÉATION D'UNE SORTIE
    public Sortie createSortie(Sortie sortie) {
        // On initialise automatiquement dateCreation et dateModification
        ZonedDateTime now = ZonedDateTime.now();
        sortie.setDateCreation(now);
        sortie.setDateModification(now);

        // Sécurisation : si x ou y sont null côté client
        if (sortie.getX() == null) {
            sortie.setX(BigDecimal.ZERO);
        }
        if (sortie.getY() == null) {
            sortie.setY(BigDecimal.ZERO);
        }

        return sortieRepository.save(sortie);
    }

    // 2) SUPPRESSION DE TOUTES LES SORTIES
    public void deleteAllSorties() {
        sortieRepository.deleteAll();
    }

    // 3) SUPPRESSION D'UNE SORTIE (PAR ID)
    public void deleteSortieById(Short id) {
        if (!sortieRepository.existsById(id)) {
            throw new RuntimeException("Sortie non trouvée pour l'ID: " + id);
        }
        sortieRepository.deleteById(id);
    }

    // 4) GET SORTIE PAR ID
    public Sortie getSortieById(Short id) {
        return sortieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sortie non trouvée pour l'ID: " + id));
    }

    // 5) GET SORTIE PAR NOM
    public Sortie getSortieByName(String name) {
        Sortie sortie = sortieRepository.findByName(name);
        if (sortie == null) {
            throw new RuntimeException("Sortie non trouvée pour le nom: " + name);
        }
        return sortie;
    }

    // 6) GET TOUTES LES SORTIES
    public List<Sortie> getAllSorties() {
        return sortieRepository.findAll();
    }

    // 7) UPDATE (PUT) - Mise à jour complète
    public Sortie updateSortieFully(Short id, Sortie sortieInput) {
        Sortie existing = sortieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sortie non trouvée pour l'ID: " + id));

        // Mise à jour complète de tous les champs modifiables
        existing.setName(sortieInput.getName());
        existing.setX(sortieInput.getX() == null ? BigDecimal.ZERO : sortieInput.getX());
        existing.setY(sortieInput.getY() == null ? BigDecimal.ZERO : sortieInput.getY());

        // Met à jour la dateModification
        existing.setDateModification(ZonedDateTime.now());

        // On ne change pas dateCreation
        return sortieRepository.save(existing);
    }

    // 8) UPDATE (PATCH) - Mise à jour partielle
    public Sortie updateSortiePartially(Short id, Sortie sortieInput) {
        Sortie existing = sortieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sortie non trouvée pour l'ID: " + id));

        // Seuls les champs non nuls sont mis à jour
        if (sortieInput.getName() != null && !sortieInput.getName().isBlank()) {
            existing.setName(sortieInput.getName());
        }
        if (sortieInput.getX() != null) {
            existing.setX(sortieInput.getX());
        }
        if (sortieInput.getY() != null) {
            existing.setY(sortieInput.getY());
        }

        // Met à jour la dateModification
        existing.setDateModification(ZonedDateTime.now());

        // Pas de modification de dateCreation
        return sortieRepository.save(existing);
    }
}
