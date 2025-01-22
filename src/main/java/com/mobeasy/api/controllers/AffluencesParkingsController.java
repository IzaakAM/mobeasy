package com.mobeasy.api.controllers;

import com.mobeasy.api.dto.AffluencesParkingsDTO;
import com.mobeasy.api.entities.AffluencesParkings;
import com.mobeasy.api.services.AffluencesParkingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/affluences-parkings")
public class AffluencesParkingsController {

    @Autowired
    private AffluencesParkingsService affluencesParkingsService;

    // 1. Créer une nouvelle affluence par parkingId et nbVehicule
    @PostMapping("/by-id")
    public ResponseEntity<AffluencesParkings> createAffluenceById(@RequestParam Short parkingId,
                                                                  @RequestParam Integer nbVehicule) {
        AffluencesParkings created = affluencesParkingsService.createAffluenceById(parkingId, nbVehicule);
        return ResponseEntity.ok(created);
    }

    // 2. Créer une nouvelle affluence par parkingName et nbVehicule
    @PostMapping("/by-name")
    public ResponseEntity<AffluencesParkings> createAffluenceByName(@RequestParam String parkingName,
                                                                    @RequestParam Integer nbVehicule) {
        AffluencesParkings created = affluencesParkingsService.createAffluenceByName(parkingName, nbVehicule);
        return ResponseEntity.ok(created);
    }

    // 3. Récupérer la dernière affluence d'un parking par ID
    @GetMapping("/last/{parkingId}")
    public ResponseEntity<AffluencesParkingsDTO> getLastAffluenceByParkingId(@PathVariable Short parkingId) {
        Optional<AffluencesParkingsDTO> dtoOpt = affluencesParkingsService.getLastAffluenceByParkingId(parkingId);
        return dtoOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Récupérer la dernière affluence d'un parking par nom
    @GetMapping("/last/by-name/{parkingName}")
    public ResponseEntity<AffluencesParkingsDTO> getLastAffluenceByParkingName(@PathVariable String parkingName) {
        Optional<AffluencesParkingsDTO> dtoOpt = affluencesParkingsService.getLastAffluenceByParkingName(parkingName);
        return dtoOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
