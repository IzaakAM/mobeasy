package com.mobeasy.api.entities;

import com.mobeasy.api.entities.Sortie;
import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Affluences_sorties")
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

    public com.mobeasy.api.entities.Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }
}
