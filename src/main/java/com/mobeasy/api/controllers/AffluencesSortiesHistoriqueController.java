package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.AffluencesSortiesHistorique;
import com.mobeasy.api.services.AffluencesSortiesHistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/affluence-sortes-historique")
public class AffluencesSortiesHistoriqueController {

    @Autowired
    private AffluencesSortiesHistoriqueService affluencesSortiesHistoriqueService;

    // GET : Récupérer l'historique des affluences d'une sortie
    @GetMapping("/{sortieId}")
    public ResponseEntity<List<AffluencesSortiesHistorique>> getHistoriqueBySortieId(@PathVariable Short sortieId) {
        return ResponseEntity.ok(affluencesSortiesHistoriqueService.findHistoriqueBySortieId(sortieId));
    }
}
