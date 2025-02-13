package com.mobeasy.api.services.mappers;

import com.mobeasy.api.entities.AffluencesSorties;
import com.mobeasy.api.entities.dto.AffluencesSortiesDTO;
import com.mobeasy.api.services.utils.DateFormatterUtil;
import org.springframework.stereotype.Service;

@Service
public class AffluenceSortieMapperService {
    public AffluencesSortiesDTO toDTO(AffluencesSorties affluencesSorties) {
        String formattedDate = DateFormatterUtil.formatZonedDateTime(affluencesSorties.getTimestamp());

        return new AffluencesSortiesDTO(
                affluencesSorties.getSortie().getName(),
                affluencesSorties.getNbVehicule(),
                formattedDate
        );
    }

}
