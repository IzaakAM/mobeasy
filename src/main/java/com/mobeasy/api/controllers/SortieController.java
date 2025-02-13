package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.Sortie;
import com.mobeasy.api.services.SortieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sorties")
@Tag(name = "Sorties", description = "Gestion des sorties")
@RequiredArgsConstructor
public class SortieController {

    private final SortieService sortieService;

    @PostMapping
    @Operation(
            summary = "Créer une nouvelle sortie",
            description = "Crée une nouvelle sortie et renvoie l'objet créé. Les champs id/dateCreation/dateModification sont calculés automatiquement.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sortie créée avec succès", content = @Content(schema = @Schema(implementation = Sortie.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Sortie> createSortie(@RequestBody Sortie sortie) {
        return new ResponseEntity<>(sortieService.createSortie(sortie), HttpStatus.CREATED);
    }

    @DeleteMapping
    @Operation(
            summary = "Supprimer toutes les sorties",
            description = "Supprime définitivement toutes les sorties de la base de données.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Toutes les sorties ont été supprimées"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Void> deleteAllSorties() {
        sortieService.deleteAllSorties();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer une sortie par ID",
            description = "Supprime la sortie correspondant à l'ID fourni.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Sortie supprimée avec succès"),
                    @ApiResponse(responseCode = "404", description = "Sortie non trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Void> deleteSortie(@PathVariable Short id) {
        sortieService.deleteSortieById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Récupérer une sortie par ID",
            description = "Retourne la sortie correspondant à l'ID fourni.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sortie récupérée avec succès", content = @Content(schema = @Schema(implementation = Sortie.class))),
                    @ApiResponse(responseCode = "404", description = "Sortie non trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Sortie> getSortieById(@PathVariable Short id) {
        Sortie sortie = sortieService.getSortieById(id);
        return ResponseEntity.ok(sortie);
    }

    @GetMapping("/by-name/{name}")
    @Operation(
            summary = "Récupérer une sortie par nom",
            description = "Retourne la sortie correspondant au nom fourni.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sortie récupérée avec succès", content = @Content(schema = @Schema(implementation = Sortie.class))),
                    @ApiResponse(responseCode = "404", description = "Sortie non trouvée pour ce nom"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Sortie> getSortieByName(@PathVariable String name) {
        Sortie sortie = sortieService.getSortieByName(name);
        return ResponseEntity.ok(sortie);
    }

    @GetMapping
    @Operation(
            summary = "Récupérer toutes les sorties",
            description = "Retourne toutes les sorties enregistrées en base.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste de toutes les sorties", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Sortie.class)))),
                    @ApiResponse(responseCode = "204", description = "Aucune sortie disponible"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<List<Sortie>> getAllSorties() {
        List<Sortie> sorties = sortieService.getAllSorties();
        if (sorties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sorties);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Mettre à jour une sortie (PUT)",
            description = "Met à jour une sortie de façon complète.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sortie mise à jour avec succès", content = @Content(schema = @Schema(implementation = Sortie.class))),
                    @ApiResponse(responseCode = "404", description = "Sortie non trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Sortie> updateSortieFully(@PathVariable Short id, @RequestBody Sortie sortie) {
        Sortie updated = sortieService.updateSortieFully(id, sortie);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Mettre à jour partiellement une sortie (PATCH)",
            description = "Met à jour certains champs sans remplacer complètement la sortie.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sortie mise à jour avec succès", content = @Content(schema = @Schema(implementation = Sortie.class))),
                    @ApiResponse(responseCode = "404", description = "Sortie non trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Sortie> updateSortiePartially(@PathVariable Short id, @RequestBody Sortie sortie) {
        Sortie updated = sortieService.updateSortiePartially(id, sortie);
        return ResponseEntity.ok(updated);
    }
}
