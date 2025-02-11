package com.mobeasy.api.entities.dto;

public record AffluencesParkingsDTO(
        String parkingName,
        int parkingCapacity,
        int numVehicles,
        String dateTime) {
}
