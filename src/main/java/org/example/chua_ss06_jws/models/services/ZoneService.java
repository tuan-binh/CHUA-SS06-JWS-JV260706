package org.example.chua_ss06_jws.models.services;

import org.example.chua_ss06_jws.models.dto.res.ZoneStatisticsResponse;

import java.util.List;

public interface ZoneService {
    List<ZoneStatisticsResponse> getAllStatWithLogic();

    List<ZoneStatisticsResponse> getAllStatWithJPQL();
}
