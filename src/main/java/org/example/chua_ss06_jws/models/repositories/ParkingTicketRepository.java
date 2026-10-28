package org.example.chua_ss06_jws.models.repositories;

import org.example.chua_ss06_jws.models.dto.res.TicketResponse;
import org.example.chua_ss06_jws.models.entities.ParkingTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket,Long> {

    Optional<ParkingTicket> findByVehicleIdAndCheckOutTimeIsNull(Long vehicleId);

    @Query("""
            SELECT new org.example.chua_ss06_jws.models.dto.res.TicketResponse(
                        pt.id,
                        pt.vehicle.licensePlate,
                        pt.zone.name,
                        pt.checkInTime,
                        pt.checkOutTime
                    ) FROM ParkingTicket pt
            WHERE pt.checkInTime > :start AND pt.checkOutTime < :end
        """)
    List<TicketResponse> getAllTicketToday(
            @Param("start") LocalDateTime start, // tody : 00:00:00
            @Param("end") LocalDateTime end // tody : 23:59:59
    );

    @Query("SELECT NEW org.example.chua_ss06_jws.models.dto.res.TicketResponse(" +
            "t.id, " +
            "t.vehicle.licensePlate, " +
            "t.zone.name, " +
            "t.checkInTime, " +
            "t.checkOutTime) " +
            "FROM ParkingTicket t " +
            "WHERE (:licensePlate IS NULL OR t.vehicle.licensePlate = :licensePlate) " +
            "AND (:fromDate IS NULL OR t.checkInTime >= :fromDate) " +
            "AND (:toDate IS NULL OR t.checkInTime <= :toDate)")
    Page<TicketResponse> searchTicketResponses(
            @Param("licensePlate") String licensePlate,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable
    );

}

