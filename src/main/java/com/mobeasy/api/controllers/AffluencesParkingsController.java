package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.dto.AffluencesParkingsDTO;
import com.mobeasy.api.entities.AffluencesParkings;
import com.mobeasy.api.services.AffluencesParkingsService;
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
@RequestMapping("/api/affluences-parkings")
@Tag(name = "Affluences des parkings", description = "Gestion des affluences des parkings")
@RequiredArgsConstructor
public class AffluencesParkingsController {

    private final AffluencesParkingsService affluencesParkingsService;

    @PostMapping("/by-id")
    @Operation(
            summary = "Créer une nouvelle affluence par ID de parking",
            description = "Crée une nouvelle entrée d'affluence pour un parking en utilisant l'ID du parking et le nombre de véhicules.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Affluence créée avec succès", content = @Content(schema = @Schema(implementation = AffluencesParkings.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<AffluencesParkings> createAffluenceById(@RequestParam Short parkingId, @RequestParam Integer nbVehicule) {
        AffluencesParkings created = affluencesParkingsService.createAffluenceById(parkingId, nbVehicule);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/by-name")
    @Operation(
            summary = "Créer une nouvelle affluence par nom de parking",
            description = "Crée une nouvelle entrée d'affluence pour un parking en utilisant le nom du parking et le nombre de véhicules.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Affluence créée avec succès", content = @Content(schema = @Schema(implementation = AffluencesParkings.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<AffluencesParkings> createAffluenceByName(@RequestParam String parkingName, @RequestParam Integer nbVehicule) {
        AffluencesParkings created = affluencesParkingsService.createAffluenceByName(parkingName, nbVehicule);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/last/{parkingId}")
    @Operation(
            summary = "Récupérer la dernière affluence d'un parking par ID",
            description = "Retourne la dernière affluence enregistrée pour un parking donné selon son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dernière affluence trouvée", content = @Content(schema = @Schema(implementation = AffluencesParkingsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<AffluencesParkingsDTO> getLastAffluenceByParkingId(@PathVariable Short parkingId) {
        Optional<AffluencesParkingsDTO> dtoOpt = affluencesParkingsService.getLastAffluenceByParkingId(parkingId);
        return dtoOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/last/by-name/{parkingName}")
    @Operation(
            summary = "Récupérer la dernière affluence d'un parking par nom",
            description = "Retourne la dernière affluence enregistrée pour un parking donné selon son nom.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dernière affluence trouvée", content = @Content(schema = @Schema(implementation = AffluencesParkingsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<AffluencesParkingsDTO> getLastAffluenceByParkingName(@PathVariable String parkingName) {
        Optional<AffluencesParkingsDTO> dtoOpt = affluencesParkingsService.getLastAffluenceByParkingName(parkingName);
        return dtoOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/by-name/{parkingName}")
    @Operation(
            summary = "Récupérer toutes les affluences d'un parking par nom",
            description = "Retourne toutes les affluences enregistrées pour un parking donné selon son nom.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste de toutes les affluences pour ce parking", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AffluencesParkingsDTO.class)))),
                    @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<List<AffluencesParkingsDTO>> getAllAffluencesByParkingName(@PathVariable String parkingName) {
        List<AffluencesParkingsDTO> affluencesList = affluencesParkingsService.getAllAffluencesByParkingName(parkingName);
        if (affluencesList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(affluencesList);
    }


    @GetMapping("/all/{parkingId}")
    @Operation(
            summary = "Récupérer toutes les affluences d'un parking par ID",
            description = "Retourne toutes les affluences enregistrées pour un parking donné selon son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste de toutes les affluences pour ce parking", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AffluencesParkingsDTO.class)))),
                    @ApiResponse(responseCode = "404", description = "Aucune affluence trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<List<AffluencesParkingsDTO>> getAllAffluencesByParkingId(@PathVariable Short parkingId) {
        List<AffluencesParkingsDTO> affluencesList = affluencesParkingsService.getAllAffluencesByParkingId(parkingId);
        if (affluencesList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(affluencesList);
    }

    @DeleteMapping("/{affluenceId}")
    @Operation(
            summary = "Supprimer une affluence par ID",
            description = "Supprime l'affluence correspondant à l'ID fourni.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Affluence supprimée avec succès"),
                    @ApiResponse(responseCode = "404", description = "Affluence non trouvée"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Void> deleteAffluenceById(@PathVariable Short affluenceId) {
        affluencesParkingsService.deleteAffluenceById(affluenceId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Supprimer toutes les affluences de parkings",
            description = "Supprime toutes les entrées d'affluences de parking dans la base de données.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Toutes les affluences ont été supprimées"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public ResponseEntity<Void> deleteAllAffluences() {
        affluencesParkingsService.deleteAllAffluences();
        return ResponseEntity.noContent().build();
    }
}
