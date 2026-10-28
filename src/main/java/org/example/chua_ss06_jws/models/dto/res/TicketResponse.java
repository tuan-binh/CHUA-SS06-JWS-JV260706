package org.example.chua_ss06_jws.models.dto.res;

import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        String licensePlate,
        String zoneName,
        LocalDateTime checkInTIme,
        LocalDateTime checkOutTime
) {
}
