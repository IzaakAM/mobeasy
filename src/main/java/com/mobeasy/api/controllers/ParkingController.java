package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.Parking;
import com.mobeasy.api.entities.User;
import com.mobeasy.api.exceptions.ForbiddenException;
import com.mobeasy.api.services.ParkingService;
import com.mobeasy.api.services.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/parkings")
@Tag(name = "Parkings", description = "Gestion des parkings")
public class ParkingController {

    private final ParkingService parkingService;
    private final AuthService authService;

    @GetMapping
    @Operation(
            summary = "Récupérer tous les parkings",
            description = "Retourne la liste de tous les parkings ayant une capacité supérieure à 0.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des parkings récupérée avec succès", content = @Content(schema = @Schema(implementation = Parking.class)))
            }
    )
    public ResponseEntity<List<Parking>> getAllParkings() {
        return ResponseEntity.ok(parkingService.findParkingsWithCapacityGreaterThan(0));
    }

    @GetMapping("/by-name")
    @Operation(
            summary = "Trouver un parking par son nom",
            description = "Retourne un parking correspondant au nom fourni.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parking trouvé", content = @Content(schema = @Schema(implementation = Parking.class))),
                    @ApiResponse(responseCode = "404", description = "Parking non trouvé")
            }
    )
    public ResponseEntity<Parking> getParkingByName(@RequestParam String name) {
        Parking parking = parkingService.findParkingByName(name);
        return parking != null ? ResponseEntity.ok(parking) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Trouver un parking par son ID",
            description = "Retourne un parking correspondant à l'identifiant fourni.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parking trouvé", content = @Content(schema = @Schema(implementation = Parking.class))),
                    @ApiResponse(responseCode = "404", description = "Parking non trouvé")
            }
    )
    public ResponseEntity<Parking> getParkingById(@PathVariable Short id) {
        Parking parking = parkingService.findParkingById(id);
        return parking != null ? ResponseEntity.ok(parking) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(
            summary = "Ajouter un nouveau parking",
            description = "Crée un nouveau parking avec les informations fournies.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Parking ajouté avec succès", content = @Content(schema = @Schema(implementation = Parking.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "403", description = "Non autorisé à ajouter un parking")
            }
    )
    public ResponseEntity<Parking> addParking(@RequestBody Parking parking, Authentication authentication) {
        User user = authService.authenticate(authentication);
        if (!user.isAdmin()) {
            throw new ForbiddenException("User is not allowed to add a parking.");
        }
        return new ResponseEntity<>(parkingService.saveParking(parking), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un parking par son ID",
            description = "Supprime le parking correspondant à l'identifiant fourni.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Parking supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Parking non trouvé")
            }
    )
    public ResponseEntity<Void> deleteParking(@PathVariable Short id) {
        parkingService.deleteParkingById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Mettre à jour un parking",
            description = "Remplace complètement les informations d'un parking existant.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parking mis à jour avec succès", content = @Content(schema = @Schema(implementation = Parking.class))),
                    @ApiResponse(responseCode = "404", description = "Parking non trouvé")
            }
    )
    public ResponseEntity<Parking> updateParking(@PathVariable Short id, @RequestBody Parking updatedParking) {
        Parking existingParking = parkingService.findParkingById(id);
        if (existingParking == null) {
            return ResponseEntity.notFound().build();
        }
        existingParking.setName(updatedParking.getName());
        existingParking.setX(updatedParking.getX());
        existingParking.setY(updatedParking.getY());
        existingParking.setCapacity(updatedParking.getCapacity());
        return ResponseEntity.ok(parkingService.saveParking(existingParking));
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Mise à jour partielle d'un parking",
            description = "Met à jour certains champs d'un parking existant sans modifier les autres.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parking mis à jour partiellement avec succès", content = @Content(schema = @Schema(implementation = Parking.class))),
                    @ApiResponse(responseCode = "404", description = "Parking non trouvé")
            }
    )
    public ResponseEntity<Parking> partialUpdateParking(@PathVariable Short id, @RequestBody Parking patchParking) {
        Parking existingParking = parkingService.findParkingById(id);
        if (existingParking == null) {
            return ResponseEntity.notFound().build();
        }
        if (patchParking.getName() != null) existingParking.setName(patchParking.getName());
        if (patchParking.getX() != null) existingParking.setX(patchParking.getX());
        if (patchParking.getY() != null) existingParking.setY(patchParking.getY());
        if (patchParking.getCapacity() != null) existingParking.setCapacity(patchParking.getCapacity());
        return ResponseEntity.ok(parkingService.saveParking(existingParking));
    }
}
