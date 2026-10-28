package org.example.chua_ss06_jws.controllers;

import lombok.RequiredArgsConstructor;
import org.example.chua_ss06_jws.models.dto.req.TicketRequest;
import org.example.chua_ss06_jws.models.dto.res.TicketResponse;
import org.example.chua_ss06_jws.models.services.ParkingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class ParkingController {
    private final ParkingService parkingService;

    @PostMapping("/check-in")
    public ResponseEntity<TicketResponse> checkIn(@RequestBody TicketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.checkIn(request));
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<TicketResponse> checkOut(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(parkingService.checkOut(vehicleId));
    }

    @GetMapping("/summary")
    public ResponseEntity<List<TicketResponse>> summary() {
        return ResponseEntity.ok(parkingService.getAllTicketToday());
    }

    @GetMapping("/history")
    public ResponseEntity<Page<TicketResponse>> histories(
            @RequestParam(name = "licensePlate") String licensePlate,
            @RequestParam(name = "formDate") LocalDateTime fromDate,
            @RequestParam(name = "toDate") LocalDateTime toDate,
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(parkingService.histories(licensePlate, fromDate, toDate, pageable));
    }

}
