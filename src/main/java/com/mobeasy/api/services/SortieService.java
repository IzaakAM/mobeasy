package com.mobeasy.api.services;

import com.mobeasy.api.entities.Sortie;
import com.mobeasy.api.repositories.SortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class SortieService {

    @Autowired
    private SortieRepository sortieRepository;

    public Sortie findSortieByName(String name) {
        return sortieRepository.findByName(name);
    }

    public List<Sortie> findSortiesAfterDate(ZonedDateTime date) {
        return sortieRepository.findByDateCreationAfter(date);
    }

    public Sortie saveSortie(Sortie sortie) {
        return sortieRepository.save(sortie);
    }

    public void deleteSortieById(Short id) {
        sortieRepository.deleteById(id);
    }
}
