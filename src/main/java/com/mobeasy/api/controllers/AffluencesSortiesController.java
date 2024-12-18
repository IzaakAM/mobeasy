package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.AffluencesSorties;
import com.mobeasy.api.services.AffluencesSortiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affluences-sorties")
public class AffluencesSortiesController {

    @Autowired
    private AffluencesSortiesService affluencesSortiesService;

    // GET : Récupérer les affluences d'une sortie
    @GetMapping("/{sortieId}")
    public ResponseEntity<List<AffluencesSorties>> getAffluencesBySortieId(@PathVariable Short sortieId) {
        return ResponseEntity.ok(affluencesSortiesService.findAffluencesBySortieId(sortieId));
    }
}
