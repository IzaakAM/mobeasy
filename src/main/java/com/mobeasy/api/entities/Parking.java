package com.mobeasy.api.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Parkings")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_seq_gen")
    @SequenceGenerator(
            name = "parking_seq_gen",
            sequenceName = "parkings_id_seq",
            allocationSize = 1
    )
    private Short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "x")
    private BigDecimal x;

    @Column(name = "y")
    private BigDecimal y;

    @Column(name = "capacity")
    private Integer capacity;

    @CreationTimestamp
    @Column(name = "date_creation", updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dateModification;

    // Getters and setters
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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