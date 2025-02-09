package com.mobeasy.api.services;

import com.mobeasy.api.dto.AffluencesParkingsDTO;
import com.mobeasy.api.entities.AffluencesParkings;
import com.mobeasy.api.util.DateFormatterUtil;
import org.springframework.stereotype.Service;

@Service
public class AffluenceParkingMapperService {

    public AffluencesParkingsDTO toDTO(AffluencesParkings affluencesParkings) {
        String formattedDate = DateFormatterUtil.formatZonedDateTime(affluencesParkings.getTimestamp());

        return new AffluencesParkingsDTO(
                affluencesParkings.getParking().getName(),
                affluencesParkings.getParking().getCapacity(),
                affluencesParkings.getNbVehicule(),
                formattedDate
        );
    }
}
