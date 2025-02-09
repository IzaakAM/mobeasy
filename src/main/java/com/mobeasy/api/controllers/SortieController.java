package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.Sortie;
import com.mobeasy.api.services.SortieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sorties")
@Tag(name = "Sorties", description = "Gestion des sorties")
public class SortieController {

    private final SortieService sortieService;

    public SortieController(SortieService sortieService) {
        this.sortieService = sortieService;
    }

    // 1) CRÉATION
    @Operation(summary = "Créer une nouvelle sortie",
            description = "Crée une nouvelle sortie et renvoie l'objet créé. Les champs id/dateCreation/dateModification sont calculés automatiquement.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sortie créée avec succès",
                    content = @Content(schema = @Schema(implementation = Sortie.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Sortie> createSortie(@RequestBody Sortie sortie) {
        return ResponseEntity.ok(sortieService.createSortie(sortie));
    }

    // 2) SUPPRESSION DE TOUTES LES SORTIES
    @Operation(summary = "Supprimer toutes les sorties",
            description = "Supprime définitivement toutes les sorties de la base de données.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Toutes les sorties ont été supprimées"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAllSorties() {
        sortieService.deleteAllSorties();
        return ResponseEntity.noContent().build();
    }

    // 3) SUPPRESSION PAR ID
    @Operation(summary = "Supprimer une sortie par ID",
            description = "Supprime la sortie correspondant à l'ID fourni (Short).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sortie supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Sortie non trouvée")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSortie(@PathVariable Short id) {
        sortieService.deleteSortieById(id);
        return ResponseEntity.noContent().build();
    }

    // 4) GET PAR ID
    @Operation(summary = "Récupérer une sortie par ID",
            description = "Retourne la sortie correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sortie récupérée avec succès",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @Schema(implementation = Sortie.class))),
            @ApiResponse(responseCode = "404", description = "Sortie non trouvée")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Sortie> getSortieById(@PathVariable Short id) {
        Sortie sortie = sortieService.getSortieById(id);
        return ResponseEntity.ok(sortie);
    }

    // 5) GET PAR NOM
    @Operation(summary = "Récupérer une sortie par nom",
            description = "Retourne la sortie correspondant au nom fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sortie récupérée avec succès",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @Schema(implementation = Sortie.class))),
            @ApiResponse(responseCode = "404", description = "Sortie non trouvée pour ce nom")
    })
    @GetMapping("/by-name/{name}")
    public ResponseEntity<Sortie> getSortieByName(@PathVariable String name) {
        Sortie sortie = sortieService.getSortieByName(name);
        return ResponseEntity.ok(sortie);
    }

    // 6) GET TOUTES LES SORTIES
    @Operation(summary = "Récupérer toutes les sorties",
            description = "Retourne toutes les sorties enregistrées en base.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste de toutes les sorties",
                    content = @io.swagger.v3.oas.annotations.media.Content(array = @ArraySchema(schema = @Schema(implementation = Sortie.class)))),
            @ApiResponse(responseCode = "204", description = "Aucune sortie disponible")
    })
    @GetMapping
    public ResponseEntity<List<Sortie>> getAllSorties() {
        List<Sortie> sorties = sortieService.getAllSorties();
        if (sorties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sorties);
    }

    // 7) MISE À JOUR (PUT) - complète
    @Operation(summary = "Mettre à jour une sortie (PUT)",
            description = "Met à jour une sortie de façon complète, en remplaçant tous les champs (sauf l'id/dateCreation/dateModification, qui sont gérés automatiquement).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sortie mise à jour avec succès",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @Schema(implementation = Sortie.class))),
            @ApiResponse(responseCode = "404", description = "Sortie non trouvée")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Sortie> updateSortieFully(@PathVariable Short id,
                                                    @RequestBody Sortie sortie) {
        Sortie updated = sortieService.updateSortieFully(id, sortie);
        return ResponseEntity.ok(updated);
    }

    // 8) MISE À JOUR (PATCH) - partielle
    @Operation(summary = "Mettre à jour partiellement une sortie (PATCH)",
            description = "Permet de mettre à jour certains champs sans remplacer complètement la sortie (id/dateCreation/dateModification ne sont pas modifiables).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sortie mise à jour avec succès",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @Schema(implementation = Sortie.class))),
            @ApiResponse(responseCode = "404", description = "Sortie non trouvée")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Sortie> updateSortiePartially(@PathVariable Short id,
                                                        @RequestBody Sortie sortie) {
        Sortie updated = sortieService.updateSortiePartially(id, sortie);
        return ResponseEntity.ok(updated);
    }
}
