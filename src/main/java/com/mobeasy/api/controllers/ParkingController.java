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
}
