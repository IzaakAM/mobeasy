package com.mobeasy.api.entities;

import com.mobeasy.api.entities.Sortie;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "Affluences_sorties")
@Tag(name = "Affluences des sorties", description = "gestion de l'affluence des sorties")
public class AffluencesSorties {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affluences_sorties_id_seq")
    @SequenceGenerator(name = "affluences_sorties_id_seq", sequenceName = "\"Affluences_sorties_id_seq\"", allocationSize = 1)
    private Short id;

    @Column(name = "nb_vehicule")
    private Integer nbVehicule;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "sortie_id", referencedColumnName = "id", nullable = false)
    private com.mobeasy.api.entities.Sortie sortie;

}
