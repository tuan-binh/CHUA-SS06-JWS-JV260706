package org.example.chua_ss06_jws.models.repositories;

import org.example.chua_ss06_jws.models.dto.res.ZoneStatisticsResponse;
import org.example.chua_ss06_jws.models.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
    @Query("""
                SELECT new org.example.chua_ss06_jws.models.dto.res.ZoneStatisticsResponse(
                        z.id,
                        z.name,
                        z.capacity,
                        z.occupiedSpots,
                        z.capacity - z.occupiedSpots
                    ) FROM Zone z
            """)
    List<ZoneStatisticsResponse> getAllZoneStatistics();
}
