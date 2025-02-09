package com.mobeasy.api.services;

import com.mobeasy.api.entities.AffluencesSorties;
import com.mobeasy.api.entities.Sortie;
import com.mobeasy.api.entities.dto.AffluencesSortiesDTO;
import com.mobeasy.api.repositories.AffluencesSortiesRepository;
import com.mobeasy.api.services.mappers.AffluenceSortieMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AffluencesSortiesService {

    @Autowired
    private AffluencesSortiesRepository affluencesSortiesRepository;

    @Autowired
    private SortieService sortieService; // Service pour récupérer Sortie par nom ou ID

    @Autowired
    private AffluenceSortieMapperService mapperService;

    // 1. Créer une nouvelle affluence avec sortieId et nbVehicule
    public AffluencesSorties createAffluenceById(Short sortieId, Integer nbVehicule) {
        Sortie sortie = sortieService.getSortieById(sortieId);
        if (sortie == null) {
            throw new RuntimeException("Sortie non trouvée pour l'ID : " + sortieId);
        }

        AffluencesSorties affluence = new AffluencesSorties();
        affluence.setSortie(sortie);
        affluence.setTimestamp(ZonedDateTime.now());

        return affluencesSortiesRepository.save(affluence);
    }

    // 2. Créer une nouvelle affluence avec sortieName et nbVehicule
    public AffluencesSorties createAffluenceByName(String sortieName, Integer nbVehicule) {
        Sortie sortie = sortieService.getSortieByName(sortieName);
        if (sortie == null) {
            throw new RuntimeException("Sortie non trouvée pour le nom : " + sortieName);
        }

        AffluencesSorties affluence = new AffluencesSorties();
        affluence.setSortie(sortie);
        affluence.setTimestamp(ZonedDateTime.now());

        return affluencesSortiesRepository.save(affluence);
    }

    // 3. Récupérer la dernière affluence d'une sortie par ID
    public Optional<AffluencesSortiesDTO> getLastAffluenceBySortieId(Short sortieId) {
        // Hypothèse: la méthode du repository s'appelle findFirstBySortieIdOrderByTimestampDesc
        Optional<AffluencesSorties> affluenceOpt =
                affluencesSortiesRepository.findFirstBySortieIdOrderByTimestampDesc(sortieId);

        return affluenceOpt.map(mapperService::toDTO);
    }

    // 4. Récupérer la dernière affluence d'une sortie par nom
    public Optional<AffluencesSortiesDTO> getLastAffluenceBySortieName(String sortieName) {
        Sortie sortie = sortieService.getSortieByName(sortieName);
        if (sortie == null) {
            return Optional.empty();
        }
        return getLastAffluenceBySortieId(sortie.getId());
    }

    // 5. Récupérer toutes les affluences d'une sortie par ID
    public List<AffluencesSortiesDTO> getAllAffluencesBySortieId(Short sortieId) {
        // Hypothèse: la méthode du repository s'appelle findBySortie_IdOrderByTimestampDesc
        List<AffluencesSorties> results =
                affluencesSortiesRepository.findBySortie_IdOrderByTimestampDesc(sortieId);

        return results.stream()
                .map(mapperService::toDTO)
                .collect(Collectors.toList());
    }

    // 6. Récupérer toutes les affluences d'une sortie par nom
    public List<AffluencesSortiesDTO> getAllAffluencesBySortieName(String sortieName) {
        // Hypothèse: la méthode du repository s'appelle findBySortie_NameOrderByTimestampDesc
        List<AffluencesSorties> results =
                affluencesSortiesRepository.findBySortie_NameOrderByTimestampDesc(sortieName);

        return results.stream()
                .map(mapperService::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteAffluenceById(Short affluenceId) {
        if (!affluencesSortiesRepository.existsById(affluenceId)) {
            throw new RuntimeException("Affluence non trouvée pour l'ID: " + affluenceId);
        }
        affluencesSortiesRepository.deleteById(affluenceId);
    }

    public void deleteAllAffluences() {
        affluencesSortiesRepository.deleteAll();
    }
}
