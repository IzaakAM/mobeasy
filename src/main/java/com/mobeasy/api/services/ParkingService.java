package com.mobeasy.api.services;

import com.mobeasy.api.entities.Parking;
import com.mobeasy.api.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public Parking findParkingByName(String name) {
        return parkingRepository.findByName(name);
    }

    public Parking findParkingById(Short id) {
        return parkingRepository.findById(id).orElse(null);
    }

    public List<Parking> findParkingsWithCapacityGreaterThan(int capacity) {
        return parkingRepository.findByCapacityGreaterThanEqual(capacity);
    }

    public Parking saveParking(Parking parking) {
        return parkingRepository.save(parking);
    }

    public void deleteParkingById(Short id) {
        parkingRepository.deleteById(id);
    }
}
