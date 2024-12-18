package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Short> {

    // Trouver un parking par son nom
    Parking findByName(String name);

    // Trouver les parkings avec une capacité minimale donnée
    List<Parking> findByCapacityGreaterThanEqual(int capacity);
}
