package org.example.chua_ss06_jws.models.dto.res;

public record ZoneStatisticsResponse(
        Long id,
        String name,
        Integer capacity,
        Integer occupiedSlots,
        Integer availableSlots
) {
}
