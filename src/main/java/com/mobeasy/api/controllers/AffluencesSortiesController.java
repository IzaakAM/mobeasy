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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/by-id")
    @Operation(
            summary = "Créer une nouvelle affluence par ID de sortie",
            description = "Crée une nouvelle entrée d'affluence pour une sortie en utilisant l'ID de la sortie et le nombre de participants.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Affluence créée avec succès", content = @Content(schema = @Schema(implementation = AffluencesSorties.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<AffluencesSorties> createAffluenceById(@RequestParam Short sortieId, @RequestParam Integer nbParticipants) {
        AffluencesSorties created = affluencesSortiesService.createAffluenceById(sortieId, nbParticipants);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/by-name")
    @Operation(
            summary = "Créer une nouvelle affluence par nom de sortie",
            description = "Crée une nouvelle entrée d'affluence pour une sortie en utilisant le nom de la sortie et le nombre de participants.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Affluence créée avec succès", content = @Content(schema = @Schema(implementation = AffluencesSorties.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<AffluencesSorties> createAffluenceByName(@RequestParam String sortieName, @RequestParam Integer nbParticipants) {
        AffluencesSorties created = affluencesSortiesService.createAffluenceByName(sortieName, nbParticipants);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/last/{sortieId}")
    @Operation(
            summary = "Récupérer la dernière affluence d'une sortie par ID",
            description = "Retourne la dernière affluence enregistrée pour une sortie donnée selon son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dernière affluence trouvée", content = @Content(schema = @Schema(implementation = AffluencesSortiesDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<AffluencesSortiesDTO> getLastAffluenceBySortieId(@PathVariable Short sortieId) {
        Optional<AffluencesSortiesDTO> dtoOpt = affluencesSortiesService.getLastAffluenceBySortieId(sortieId);
        return dtoOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/{sortieId}")
    @Operation(
            summary = "Récupérer toutes les affluences d'une sortie par ID",
            description = "Retourne toutes les affluences enregistrées pour une sortie donnée selon son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste de toutes les affluences pour cette sortie", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AffluencesSortiesDTO.class)))),
                    @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<List<AffluencesSortiesDTO>> getAllAffluencesBySortieId(@PathVariable Short sortieId) {
        List<AffluencesSortiesDTO> affluencesList = affluencesSortiesService.getAllAffluencesBySortieId(sortieId);
        if (affluencesList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(affluencesList);
    }

    @GetMapping("/last/by-name/{SortieName}")
    @Operation(
            summary = "Obtenir la dernière affluence d'une sortie par son nom",
            description = "Retourne la dernière affluence enregistrée pour une sortie donné selon son nom.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dernière affluence trouvée", content = @Content(schema = @Schema(implementation = AffluencesSortiesDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<AffluencesSortiesDTO> getLastAffluenceBySortieName(@PathVariable String sortieName) {
        Optional<AffluencesSortiesDTO> dtoOpt = affluencesSortiesService.getLastAffluenceBySortieName(sortieName);
        return dtoOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/by-name/{sortieName}")
    @Operation(
            summary = "Obtenir toutes les affluences d'une sortie par son nom",
            description = "Retourne toutes les affluences enregistrées pour une sortie donné selon son nom.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste de toutes les affluences pour cette sortie", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AffluencesSortiesDTO.class)))),
                    @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<List<AffluencesSortiesDTO>> getAllAffluencesBySortieName(@PathVariable String sortieName) {
        List<AffluencesSortiesDTO> affluencesList = affluencesSortiesService.getAllAffluencesBySortieName(sortieName);
        if (affluencesList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(affluencesList);
    }

    @DeleteMapping
    @Operation(
            summary = "Supprimer toutes les affluences de sorties",
            description = "Supprime toutes les entrées d'affluences de sorties dans la base de données.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Toutes les affluences ont été supprimées"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Void> deleteAllAffluences() {
        affluencesSortiesService.deleteAllAffluences();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-id/{affluenceId}")
    @Operation(
            summary = "Supprimer une affluence de sortie par son ID",
            description = "Supprime une affluence de sortie spécifique en utilisant son ID.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Affluence supprimée avec succès"),
                    @ApiResponse(responseCode = "404", description = "Affluence non trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Void> deleteAffluenceSortieById(@PathVariable Short affluenceId) {
        affluencesSortiesService.deleteAffluenceById(affluenceId);
        return ResponseEntity.noContent().build();
    }
}
