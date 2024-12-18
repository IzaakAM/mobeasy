package com.mobeasy.api.entities;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Affluences_parkings")
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

    // Getters et setters
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Integer getNbVehicule() {
        return nbVehicule;
    }

    public void setNbVehicule(Integer nbVehicule) {
        this.nbVehicule = nbVehicule;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
