package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.AffluencesParkings;
import com.mobeasy.api.services.AffluencesParkingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/affluences-parkings")
public class AffluencesParkingsController {

    @Autowired
    private AffluencesParkingsService affluencesParkingsService;

    // GET : Récupérer les affluences d'un parking
    @GetMapping("/{parkingId}")
    public ResponseEntity<List<AffluencesParkings>> getAffluencesByParkingId(@PathVariable Short parkingId) {
        return ResponseEntity.ok(affluencesParkingsService.findAffluencesByParkingId(parkingId));
    }

    // POST : Ajouter une nouvelle affluence
    @PostMapping
    public ResponseEntity<AffluencesParkings> addAffluence(@RequestBody AffluencesParkings affluence) {
        return ResponseEntity.ok(affluencesParkingsService.saveAffluence(affluence));
    }
}
