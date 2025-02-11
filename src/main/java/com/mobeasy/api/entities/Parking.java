package com.mobeasy.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
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

}