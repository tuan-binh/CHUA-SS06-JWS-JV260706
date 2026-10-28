package org.example.chua_ss06_jws.models.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.chua_ss06_jws.models.dto.res.ZoneStatisticsResponse;
import org.example.chua_ss06_jws.models.entities.Zone;
import org.example.chua_ss06_jws.models.repositories.ZoneRepository;
import org.example.chua_ss06_jws.models.services.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {
    private final ZoneRepository zoneRepository;

    @Override
    public List<ZoneStatisticsResponse> getAllStatWithLogic() {
        List<Zone> zones = zoneRepository.findAll();
        return zones.stream().map(
                z -> new ZoneStatisticsResponse(
                        z.getId(),
                        z.getName(),
                        z.getCapacity(),
                        z.getOccupiedSpots(),
                        z.getCapacity() - z.getOccupiedSpots()
                )
        ).toList();
    }

    @Override
    public List<ZoneStatisticsResponse> getAllStatWithJPQL() {
        return zoneRepository.getAllZoneStatistics();
    }
}
