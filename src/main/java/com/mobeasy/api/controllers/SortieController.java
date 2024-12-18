package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.Sortie;
import com.mobeasy.api.services.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sorties")
public class SortieController {

    @Autowired
    private SortieService sortieService;

    // GET : Récupérer toutes les sorties
    @GetMapping
    public ResponseEntity<List<Sortie>> getAllSorties() {
        return ResponseEntity.ok(sortieService.findSortiesAfterDate(ZonedDateTime.now().minusYears(1)));
    }

    // GET : Trouver une sortie par son nom
    @GetMapping("/by-name")
    public ResponseEntity<Sortie> getSortieByName(@RequestParam String name) {
        return ResponseEntity.ok(sortieService.findSortieByName(name));
    }

    // POST : Ajouter une nouvelle sortie
    @PostMapping
    public ResponseEntity<Sortie> addSortie(@RequestBody Sortie sortie) {
        return ResponseEntity.ok(sortieService.saveSortie(sortie));
    }

    // DELETE : Supprimer une sortie par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSortie(@PathVariable Short id) {
        sortieService.deleteSortieById(id);
        return ResponseEntity.noContent().build();
    }
}
