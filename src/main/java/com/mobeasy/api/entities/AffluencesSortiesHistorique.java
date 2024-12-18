package com.mobeasy.api.entities;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Affluence_sortes_historique")
public class AffluencesSortiesHistorique {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affluence_sortes_historique_id_seq")
    @SequenceGenerator(name = "affluence_sortes_historique_id_seq", sequenceName = "\"Affluence_sortes_historique_id_seq\"", allocationSize = 1)
    private Short id;

    @Column(name = "nb_vehicule")
    private Integer nbVehicule;

    @Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "sortie_id", referencedColumnName = "id", nullable = false)
    private Sortie sortie;

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

    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }
}
