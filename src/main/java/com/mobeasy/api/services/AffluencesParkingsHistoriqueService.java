package com.mobeasy.api.services;

import com.mobeasy.api.entities.AffluencesParkingsHistorique;
import com.mobeasy.api.repositories.AffluencesParkingsHistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AffluencesParkingsHistoriqueService {

    @Autowired
    private AffluencesParkingsHistoriqueRepository affluencesParkingsHistoriqueRepository;

    public List<AffluencesParkingsHistorique> findHistoriqueByParkingId(Short parkingId) {
        return affluencesParkingsHistoriqueRepository.findByParkingId(parkingId);
    }

    public List<AffluencesParkingsHistorique> findHistoriqueAfterTimestamp(ZonedDateTime timestamp) {
        return affluencesParkingsHistoriqueRepository.findByTimestampAfter(timestamp);
    }

    public AffluencesParkingsHistorique saveHistorique(AffluencesParkingsHistorique historique) {
        return affluencesParkingsHistoriqueRepository.save(historique);
    }

    public void deleteHistoriqueById(Short id) {
        affluencesParkingsHistoriqueRepository.deleteById(id);
    }
}
