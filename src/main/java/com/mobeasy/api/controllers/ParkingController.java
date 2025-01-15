package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.Parking;
import com.mobeasy.api.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    // GET : Récupérer tous les parkings
    @GetMapping
    public ResponseEntity<List<Parking>> getAllParkings() {
        return ResponseEntity.ok(parkingService.findParkingsWithCapacityGreaterThan(0));
    }

    // GET : Trouver un parking par son nom
    @GetMapping("/by-name")
    public ResponseEntity<Parking> getParkingByName(@RequestParam String name) {
        return ResponseEntity.ok(parkingService.findParkingByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parking> getParkingById(@PathVariable Short id) {
        Parking parking = parkingService.findParkingById(id);
        if (parking != null) {
            return ResponseEntity.ok(parking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST : Ajouter un nouveau parking
    @PostMapping
    public ResponseEntity<Parking> addParking(@RequestBody Parking parking) {
        return ResponseEntity.ok(parkingService.saveParking(parking));
    }

    // DELETE : Supprimer un parking par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParking(@PathVariable Short id) {
        parkingService.deleteParkingById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parking> updateParking(@PathVariable Short id, @RequestBody Parking updatedParking) {
        Parking existingParking = parkingService.findParkingById(id);

        if (existingParking == null) {
            return ResponseEntity.notFound().build();
        }

        // On remplace tous les champs pertinents (mise à jour complète)
        existingParking.setName(updatedParking.getName());
        existingParking.setX(updatedParking.getX());
        existingParking.setY(updatedParking.getY());
        existingParking.setCapacity(updatedParking.getCapacity());
        // Si vous avez d'autres champs à mettre à jour, n'oubliez pas de les copier.

        // On enregistre en base
        Parking savedParking = parkingService.saveParking(existingParking);

        return ResponseEntity.ok(savedParking);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Parking> partialUpdateParking(
            @PathVariable Short id,
            @RequestBody Parking patchParking
    ) {
        // 1) Récupérer l'entité existante depuis la DB via le Service/Repository
        Parking existingParking = parkingService.findParkingById(id);

        // 2) Gérer le cas où l’ID n’existe pas
        if (existingParking == null) {
            return ResponseEntity.notFound().build();
        }

        // 3) Mettre à jour seulement les champs non-nuls.
        //    Si patchParking.getXXX() == null, on ne touche pas à la valeur existante.

        // Nom
        if (patchParking.getName() != null) {
            existingParking.setName(patchParking.getName());
        }

        // Coordonnées X
        if (patchParking.getX() != null) {
            existingParking.setX(patchParking.getX());
        }

        // Coordonnées Y
        if (patchParking.getY() != null) {
            existingParking.setY(patchParking.getY());
        }

        // Capacité
        if (patchParking.getCapacity() != null) {
            existingParking.setCapacity(patchParking.getCapacity());
        }


        // 4) Sauvegarder la version modifiée en BDD
        Parking savedParking = parkingService.saveParking(existingParking);

        // 5) Retourner la ressource mise à jour
        return ResponseEntity.ok(savedParking);
    }
}
