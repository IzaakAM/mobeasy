package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.AffluencesSorties;
import com.mobeasy.api.entities.dto.AffluencesSortiesDTO;
import com.mobeasy.api.services.AffluencesSortiesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/affluences-sorties")
@Tag(name = "Affluences des sorties", description = "Gestion des affluences des sorties")
@RequiredArgsConstructor
public class AffluencesSortiesController {

    private final AffluencesSortiesService affluencesSortiesService;

    // =========================================================================
    // 1) CRÉER UNE NOUVELLE AFFLUENCE PAR ID DE SORTIE
    // =========================================================================
    @Operation(summary = "Créer une nouvelle affluence par ID de sortie",
            description = "Crée une nouvelle entrée d'affluence pour une sortie en utilisant l'ID de la sortie et le nombre de participants.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Affluence créée avec succès",
                    content = @Content(schema = @Schema(implementation = AffluencesSorties.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/by-id")
    public ResponseEntity<AffluencesSorties> createAffluenceById(
            @Parameter(description = "ID de la sortie", required = true) @RequestParam Short sortieId,
            @Parameter(description = "Nombre de participants", required = true) @RequestParam Integer nbParticipants) {

        AffluencesSorties created = affluencesSortiesService.createAffluenceById(sortieId, nbParticipants);
        return ResponseEntity.ok(created);
    }

    // =========================================================================
    // 2) CRÉER UNE NOUVELLE AFFLUENCE PAR NOM DE SORTIE
    // =========================================================================
    @Operation(summary = "Créer une nouvelle affluence par nom de sortie",
            description = "Crée une nouvelle entrée d'affluence pour une sortie en utilisant le nom de la sortie et le nombre de participants.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Affluence créée avec succès",
                    content = @Content(schema = @Schema(implementation = AffluencesSorties.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/by-name")
    public ResponseEntity<AffluencesSorties> createAffluenceByName(
            @Parameter(description = "Nom de la sortie", required = true) @RequestParam String sortieName,
            @Parameter(description = "Nombre de participants", required = true) @RequestParam Integer nbParticipants) {

        AffluencesSorties created = affluencesSortiesService.createAffluenceByName(sortieName, nbParticipants);
        return ResponseEntity.ok(created);
    }

    // =========================================================================
    // 3) RÉCUPÉRER LA DERNIÈRE AFFLUENCE PAR ID DE SORTIE
    // =========================================================================
    @Operation(summary = "Récupérer la dernière affluence d'une sortie par ID",
            description = "Retourne la dernière affluence enregistrée pour une sortie donnée selon son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dernière affluence trouvée",
                    content = @Content(schema = @Schema(implementation = AffluencesSortiesDTO.class))),
            @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée")
    })
    @GetMapping("/last/{sortieId}")
    public ResponseEntity<AffluencesSortiesDTO> getLastAffluenceBySortieId(
            @Parameter(description = "ID de la sortie", required = true) @PathVariable Short sortieId) {

        Optional<AffluencesSortiesDTO> dtoOpt = affluencesSortiesService.getLastAffluenceBySortieId(sortieId);
        return dtoOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =========================================================================
    // 4) RÉCUPÉRER LA DERNIÈRE AFFLUENCE PAR NOM DE SORTIE
    // =========================================================================
    @Operation(summary = "Récupérer la dernière affluence d'une sortie par nom",
            description = "Retourne la dernière affluence enregistrée pour une sortie donnée selon son nom.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dernière affluence trouvée",
                    content = @Content(schema = @Schema(implementation = AffluencesSortiesDTO.class))),
            @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée")
    })
    @GetMapping("/last/by-name/{sortieName}")
    public ResponseEntity<AffluencesSortiesDTO> getLastAffluenceBySortieName(
            @Parameter(description = "Nom de la sortie", required = true) @PathVariable String sortieName) {

        Optional<AffluencesSortiesDTO> dtoOpt = affluencesSortiesService.getLastAffluenceBySortieName(sortieName);
        return dtoOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =========================================================================
    // 5) RÉCUPÉRER TOUTES LES AFFLUENCES PAR ID DE SORTIE
    // =========================================================================
    @Operation(summary = "Récupérer toutes les affluences d'une sortie par ID",
            description = "Retourne toutes les affluences enregistrées pour une sortie donnée selon son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste de toutes les affluences pour cette sortie",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AffluencesSortiesDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée pour cet ID")
    })
    @GetMapping("/all/{sortieId}")
    public ResponseEntity<List<AffluencesSortiesDTO>> getAllAffluencesBySortieId(
            @Parameter(description = "ID de la sortie", required = true) @PathVariable Short sortieId) {

        List<AffluencesSortiesDTO> affluencesList = affluencesSortiesService.getAllAffluencesBySortieId(sortieId);
        if (affluencesList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(affluencesList);
    }

    // =========================================================================
    // 6) RÉCUPÉRER TOUTES LES AFFLUENCES PAR NOM DE SORTIE
    // =========================================================================
    @Operation(summary = "Récupérer toutes les affluences d'une sortie par nom",
            description = "Retourne toutes les affluences enregistrées pour une sortie donnée selon son nom.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste de toutes les affluences pour cette sortie",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AffluencesSortiesDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée pour ce nom")
    })
    @GetMapping("/all/by-name/{sortieName}")
    public ResponseEntity<List<AffluencesSortiesDTO>> getAllAffluencesBySortieName(
            @Parameter(description = "Nom de la sortie", required = true) @PathVariable String sortieName) {

        List<AffluencesSortiesDTO> affluencesList = affluencesSortiesService.getAllAffluencesBySortieName(sortieName);
        if (affluencesList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(affluencesList);
    }

    @Operation(summary = "Supprimer une affluence par ID",
            description = "Supprime l'affluence correspondant à l'ID fourni (de l'entité AffluencesSorties).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Affluence supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Affluence non trouvée")
    })
    @DeleteMapping("/{affluenceId}")
    public ResponseEntity<Void> deleteAffluenceById(
            @Parameter(description = "ID de l'affluence", required = true) @PathVariable Short affluenceId) {

        affluencesSortiesService.deleteAffluenceById(affluenceId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "Supprimer toutes les affluences de sorties",
            description = "Supprime toutes les entrées d'affluences de sorties dans la base de données.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Toutes les affluences ont été supprimées"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    public ResponseEntity<Void> deleteAllAffluences() {
        affluencesSortiesService.deleteAllAffluences();
        return ResponseEntity.noContent().build();
    }
}
