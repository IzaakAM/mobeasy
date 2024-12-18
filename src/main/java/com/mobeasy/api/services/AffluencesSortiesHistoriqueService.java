package com.mobeasy.api.services;

import com.mobeasy.api.entities.AffluencesSortiesHistorique;
import com.mobeasy.api.repositories.AffluencesSortiesHistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AffluencesSortiesHistoriqueService {

    @Autowired
    private AffluencesSortiesHistoriqueRepository affluencesSortiesHistoriqueRepository;

    public List<AffluencesSortiesHistorique> findHistoriqueBySortieId(Short sortieId) {
        return affluencesSortiesHistoriqueRepository.findBySortieId(sortieId);
    }

    public List<AffluencesSortiesHistorique> findHistoriqueAfterTimestamp(ZonedDateTime timestamp) {
        return affluencesSortiesHistoriqueRepository.findByTimestampAfter(timestamp);
    }

    public AffluencesSortiesHistorique saveHistorique(AffluencesSortiesHistorique historique) {
        return affluencesSortiesHistoriqueRepository.save(historique);
    }

    public void deleteHistoriqueById(Short id) {
        affluencesSortiesHistoriqueRepository.deleteById(id);
    }
}
