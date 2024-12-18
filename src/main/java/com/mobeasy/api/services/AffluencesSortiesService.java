package com.mobeasy.api.services;

import com.mobeasy.api.entities.AffluencesSorties;
import com.mobeasy.api.repositories.AffluencesSortiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AffluencesSortiesService {

    @Autowired
    private AffluencesSortiesRepository affluencesSortiesRepository;

    public List<AffluencesSorties> findAffluencesBySortieId(Short sortieId) {
        return affluencesSortiesRepository.findBySortieId(sortieId);
    }

    public List<AffluencesSorties> findAffluencesAfterTimestamp(ZonedDateTime timestamp) {
        return affluencesSortiesRepository.findByTimestampAfter(timestamp);
    }

    public AffluencesSorties saveAffluence(AffluencesSorties affluence) {
        return affluencesSortiesRepository.save(affluence);
    }

    public void deleteAffluenceById(Short id) {
        affluencesSortiesRepository.deleteById(id);
    }
}
