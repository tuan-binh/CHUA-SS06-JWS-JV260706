package org.example.chua_ss06_jws.models.dto.res;

import org.example.chua_ss06_jws.models.constants.VehicleType;

public record VehicleResponse(
        Long id,
        String licensePlate,
        String color,
        VehicleType vehicleType
) {
}
