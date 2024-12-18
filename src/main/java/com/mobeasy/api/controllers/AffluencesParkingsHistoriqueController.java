package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.AffluencesParkingsHistorique;
import com.mobeasy.api.services.AffluencesParkingsHistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/affluences-parkings-historique")
public class AffluencesParkingsHistoriqueController {

    @Autowired
    private AffluencesParkingsHistoriqueService affluencesParkingsHistoriqueService;

    // GET : Récupérer l'historique des affluences d'un parking
    @GetMapping("/{parkingId}")
    public ResponseEntity<List<AffluencesParkingsHistorique>> getHistoriqueByParkingId(@PathVariable Short parkingId) {
        return ResponseEntity.ok(affluencesParkingsHistoriqueService.findHistoriqueByParkingId(parkingId));
    }
}
