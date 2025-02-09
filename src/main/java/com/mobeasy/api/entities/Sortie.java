package com.mobeasy.api.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Sorties")
public class Sortie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sorties_id_seq")
    @SequenceGenerator(name = "sorties_id_seq", sequenceName = "\"Sorties_id_seq\"", allocationSize = 1)
    private Short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "x")
    private BigDecimal x;

    @Column(name = "y")
    private BigDecimal y;

    @Column(name = "date_creation", updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dateCreation;

    @Column(name = "date_modification", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dateModification;

    // Getters et setters
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ZonedDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(ZonedDateTime dateModification) {
        this.dateModification = dateModification;
    }
}
