package com.mobeasy.api.services;

import com.mobeasy.api.entities.Parking;
import com.mobeasy.api.repositories.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;

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

    public void deleteAllParkings() {
        parkingRepository.deleteAll();
    }
}
