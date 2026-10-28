package org.example.chua_ss06_jws.models.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.chua_ss06_jws.models.dto.req.TicketRequest;
import org.example.chua_ss06_jws.models.dto.res.TicketResponse;
import org.example.chua_ss06_jws.models.entities.ParkingTicket;
import org.example.chua_ss06_jws.models.entities.Vehicle;
import org.example.chua_ss06_jws.models.entities.Zone;
import org.example.chua_ss06_jws.models.repositories.ParkingTicketRepository;
import org.example.chua_ss06_jws.models.repositories.VehicleRepository;
import org.example.chua_ss06_jws.models.repositories.ZoneRepository;
import org.example.chua_ss06_jws.models.services.ParkingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {
    private final ZoneRepository zoneRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingTicketRepository parkingTicketRepository;

    @Override
    public TicketResponse checkIn(TicketRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.vehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not fonud"));
        Zone zone = zoneRepository.findById(request.zoneId()).orElseThrow(() -> new RuntimeException("Zone not found"));

        // trường hợp đúng
        if (zone.getOccupiedSpots() < zone.getCapacity()) {
            ParkingTicket parkingTicket = ParkingTicket.builder()
                    .checkInTime(LocalDateTime.now())
                    .vehicle(vehicle)
                    .zone(zone)
                    .build();
            parkingTicket = parkingTicketRepository.save(parkingTicket);
            zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
            zoneRepository.save(zone);
            return new TicketResponse(
                    parkingTicket.getId(),
                    parkingTicket.getVehicle().getLicensePlate(),
                    parkingTicket.getZone().getName(),
                    parkingTicket.getCheckInTime(),
                    parkingTicket.getCheckOutTime()
            );
        }
        throw new RuntimeException("Không đủ số lượng chỗ để gửi xe");
    }

    @Override
    public TicketResponse checkOut(Long vehicleId) {
        if (vehicleRepository.existsById(vehicleId)) {
            ParkingTicket parkingTicket = parkingTicketRepository.findByVehicleIdAndCheckOutTimeIsNull(vehicleId).orElseThrow(() -> new RuntimeException("parking ticket not found"));

            parkingTicket.setCheckOutTime(LocalDateTime.now());

            parkingTicketRepository.save(parkingTicket);

            Zone zone = parkingTicket.getZone();
            zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);
            zoneRepository.save(zone);
            return new TicketResponse(
                    parkingTicket.getId(),
                    parkingTicket.getVehicle().getLicensePlate(),
                    parkingTicket.getZone().getName(),
                    parkingTicket.getCheckInTime(),
                    parkingTicket.getCheckOutTime()
            );
        }
        throw new RuntimeException("Vehicle not found");
    }

    @Override
    public List<TicketResponse> getAllTicketToday() {
        LocalDate timeNow = LocalDate.now();

        LocalDateTime start = LocalDateTime.of(timeNow.getYear(),timeNow.getMonth(),timeNow.getDayOfMonth(),0,0,0);
        LocalDateTime end = LocalDateTime.of(timeNow.getYear(),timeNow.getMonth(),timeNow.getDayOfMonth(),23,59,59);

        return parkingTicketRepository.getAllTicketToday(start,end);
    }

    @Override
    public Page<TicketResponse> histories(String licensePlate, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable) {
        return parkingTicketRepository.searchTicketResponses(licensePlate, fromDate, toDate, pageable);
    }
}
