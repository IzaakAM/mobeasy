package com.mobeasy.api.dto;

import java.util.Objects;

public record AffluencesParkingsDTO(
        String parkingName,
        int parkingCapacity,
        int numVehicles,
        String dateTime) {
}
