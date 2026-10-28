package org.example.chua_ss06_jws.models.services;

import org.example.chua_ss06_jws.models.dto.req.TicketRequest;
import org.example.chua_ss06_jws.models.dto.res.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingService {

    // check in
    TicketResponse checkIn(TicketRequest request);

    // check out
    TicketResponse checkOut(Long vehicleId);

    List<TicketResponse> getAllTicketToday();

    Page<TicketResponse> histories(String licensePlate, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

}
