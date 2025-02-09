package com.mobeasy.api.services;

import com.mobeasy.api.dto.AffluencesParkingsDTO;
import com.mobeasy.api.entities.AffluencesParkings;
import com.mobeasy.api.entities.Parking;
import com.mobeasy.api.repositories.AffluencesParkingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AffluencesParkingsService {

    @Autowired
    private AffluencesParkingsRepository affluencesParkingsRepository;

    @Autowired
    private ParkingService parkingService; // Service pour récupérer Parking par nom ou ID

    @Autowired
    private AffluenceParkingMapperService mapperService;

    // 1. Créer une nouvelle affluence avec parkingId et nbVehicule
    public AffluencesParkings createAffluenceById(Short parkingId, Integer nbVehicule) {
        Parking parking = parkingService.findParkingById(parkingId);
        if (parking == null) {
            throw new RuntimeException("Parking non trouvé");
        }
        AffluencesParkings affluence = new AffluencesParkings();
        affluence.setParking(parking);
        affluence.setNbVehicule(nbVehicule);
        affluence.setTimestamp(ZonedDateTime.now());
        return affluencesParkingsRepository.save(affluence);
    }

    // 2. Créer une nouvelle affluence avec parkingName et nbVehicule
    public AffluencesParkings createAffluenceByName(String parkingName, Integer nbVehicule) {
        Parking parking = parkingService.findParkingByName(parkingName);
        if (parking == null) {
            throw new RuntimeException("Parking non trouvé");
        }
        AffluencesParkings affluence = new AffluencesParkings();
        affluence.setParking(parking);
        affluence.setNbVehicule(nbVehicule);
        affluence.setTimestamp(ZonedDateTime.now());
        return affluencesParkingsRepository.save(affluence);
    }

    // 3. Récupérer la dernière affluence d'un parking par ID
    public Optional<AffluencesParkingsDTO> getLastAffluenceByParkingId(Short parkingId) {
        //Optional<AffluencesParkings> affluenceOpt = affluencesParkingsRepository.findTopByParkingIdOrderByTimestampDesc(parkingId);
        //return affluenceOpt.map(mapperService::toDTO);

        Optional<AffluencesParkings> affluenceOpt = affluencesParkingsRepository.findFirstByParkingIdOrderByTimestampDesc(parkingId);
        return affluenceOpt.map(mapperService::toDTO);
    }

    // 4. Récupérer la dernière affluence d'un parking par nom
    public Optional<AffluencesParkingsDTO> getLastAffluenceByParkingName(String parkingName) {
        Parking parking = parkingService.findParkingByName(parkingName);
        if (parking == null) {
            return Optional.empty();
        }
        return getLastAffluenceByParkingId(parking.getId());
    }

    public List<AffluencesParkingsDTO> getAllAffluencesByParkingId(Short parkingId) {
        List<AffluencesParkings> results =
                affluencesParkingsRepository.findByParking_IdOrderByTimestampDesc(parkingId);

        return results.stream()
                .map(mapperService::toDTO)
                .collect(Collectors.toList());
    }

    public List<AffluencesParkingsDTO> getAllAffluencesByParkingName(String parkingName) {
        List<AffluencesParkings> results =
                affluencesParkingsRepository.findByParking_NameOrderByTimestampDesc(parkingName);

        return results.stream()
                .map(mapperService::toDTO)
                .collect(Collectors.toList());
    }
}

