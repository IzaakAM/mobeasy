package com.mobeasy.api.services;

import com.mobeasy.api.entities.AffluencesParkings;
import com.mobeasy.api.repositories.AffluencesParkingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AffluencesParkingsService {

    @Autowired
    private AffluencesParkingsRepository affluencesParkingsRepository;

    public List<AffluencesParkings> findAffluencesByParkingId(Short parkingId) {
        return affluencesParkingsRepository.findByParkingId(parkingId);
    }

    public List<AffluencesParkings> findAffluencesAfterTimestamp(ZonedDateTime timestamp) {
        return affluencesParkingsRepository.findByTimestampAfter(timestamp);
    }

    public AffluencesParkings saveAffluence(AffluencesParkings affluence) {
        return affluencesParkingsRepository.save(affluence);
    }

    public void deleteAffluenceById(Short id) {
        affluencesParkingsRepository.deleteById(id);
    }
}
