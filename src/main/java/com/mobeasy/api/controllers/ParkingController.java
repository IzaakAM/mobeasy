package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.Parking;
import com.mobeasy.api.services.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Operation(summary = "Récupérer tous les parkings",
            description = "Retourne la liste de tous les parkings ayant une capacité supérieure à 0.")
    @ApiResponse(responseCode = "200", description = "Liste des parkings récupérée avec succès",
            content = @Content(schema = @Schema(implementation = Parking.class)))
    @GetMapping
    public ResponseEntity<List<Parking>> getAllParkings() {
        return ResponseEntity.ok(parkingService.findParkingsWithCapacityGreaterThan(0));
    }

    @Operation(summary = "Trouver un parking par son nom",
            description = "Retourne un parking correspondant au nom fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking trouvé",
                    content = @Content(schema = @Schema(implementation = Parking.class))),
            @ApiResponse(responseCode = "404", description = "Parking non trouvé")
    })
    @GetMapping("/by-name")
    public ResponseEntity<Parking> getParkingByName(
            @Parameter(description = "Nom du parking", required = true) @RequestParam String name) {
        Parking parking = parkingService.findParkingByName(name);
        if (parking != null) {
            return ResponseEntity.ok(parking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Trouver un parking par son ID",
            description = "Retourne un parking correspondant à l'identifiant fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking trouvé",
                    content = @Content(schema = @Schema(implementation = Parking.class))),
            @ApiResponse(responseCode = "404", description = "Parking non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Parking> getParkingById(
            @Parameter(description = "ID du parking", required = true) @PathVariable Short id) {
        Parking parking = parkingService.findParkingById(id);
        if (parking != null) {
            return ResponseEntity.ok(parking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Ajouter un nouveau parking",
            description = "Crée un nouveau parking avec les informations fournies dans le corps de la requête.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking ajouté avec succès",
                    content = @Content(schema = @Schema(implementation = Parking.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Parking> addParking(
            @Parameter(description = "Objet Parking à ajouter", required = true)
            @RequestBody Parking parking) {
        return ResponseEntity.ok(parkingService.saveParking(parking));
    }

    @Operation(summary = "Supprimer un parking par son ID",
            description = "Supprime le parking correspondant à l'identifiant fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Parking supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Parking non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParking(
            @Parameter(description = "ID du parking à supprimer", required = true) @PathVariable Short id) {
        parkingService.deleteParkingById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Mettre à jour un parking",
            description = "Remplace complètement les informations d'un parking existant par celles fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking mis à jour avec succès",
                    content = @Content(schema = @Schema(implementation = Parking.class))),
            @ApiResponse(responseCode = "404", description = "Parking non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Parking> updateParking(
            @Parameter(description = "ID du parking à mettre à jour", required = true) @PathVariable Short id,
            @Parameter(description = "Objets Parking avec les nouvelles informations", required = true) @RequestBody Parking updatedParking) {
        Parking existingParking = parkingService.findParkingById(id);

        if (existingParking == null) {
            return ResponseEntity.notFound().build();
        }

        existingParking.setName(updatedParking.getName());
        existingParking.setX(updatedParking.getX());
        existingParking.setY(updatedParking.getY());
        existingParking.setCapacity(updatedParking.getCapacity());

        Parking savedParking = parkingService.saveParking(existingParking);
        return ResponseEntity.ok(savedParking);
    }

    @Operation(summary = "Mise à jour partielle d'un parking",
            description = "Met à jour certains champs d'un parking existant sans modifier les autres.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking mis à jour partiellement avec succès",
                    content = @Content(schema = @Schema(implementation = Parking.class))),
            @ApiResponse(responseCode = "404", description = "Parking non trouvé")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Parking> partialUpdateParking(
            @Parameter(description = "ID du parking à mettre à jour partiellement", required = true) @PathVariable Short id,
            @Parameter(description = "Objets Parking avec les champs à mettre à jour", required = true) @RequestBody Parking patchParking) {

        Parking existingParking = parkingService.findParkingById(id);
        if (existingParking == null) {
            return ResponseEntity.notFound().build();
        }

        if (patchParking.getName() != null) {
            existingParking.setName(patchParking.getName());
        }
        if (patchParking.getX() != null) {
            existingParking.setX(patchParking.getX());
        }
        if (patchParking.getY() != null) {
            existingParking.setY(patchParking.getY());
        }
        if (patchParking.getCapacity() != null) {
            existingParking.setCapacity(patchParking.getCapacity());
        }

        Parking savedParking = parkingService.saveParking(existingParking);
        return ResponseEntity.ok(savedParking);
    }
}
