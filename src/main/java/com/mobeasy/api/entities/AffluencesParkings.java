package com.mobeasy.api.entities;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "Affluences_parkings")
@Tag(name = "Affluences des parkings", description = "gestion de l'affluence des parkings")
public class AffluencesParkings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affluences_parkings_id_seq")
    @SequenceGenerator(name = "affluences_parkings_id_seq", sequenceName = "\"Affluences_parkings_id_seq\"", allocationSize = 1)
    private Short id;

    @Column(name = "nb_vehicule")
    private Integer nbVehicule;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "parking_id", referencedColumnName = "id", nullable = false)
    private Parking parking;
}
