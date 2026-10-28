package org.example.chua_ss06_jws.controllers;

import lombok.RequiredArgsConstructor;
import org.example.chua_ss06_jws.models.dto.res.ZoneStatisticsResponse;
import org.example.chua_ss06_jws.models.services.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ZoneController {
    private final ZoneService zoneService;

    @GetMapping("/v1/zones/stats")
    public ResponseEntity<List<ZoneStatisticsResponse>> findAllWithLogic() {
        return ResponseEntity.ok(zoneService.getAllStatWithLogic());
    }

    @GetMapping("/v2/zones/stats")
    public ResponseEntity<List<ZoneStatisticsResponse>> findAllWithJPQL() {
        return ResponseEntity.ok(zoneService.getAllStatWithJPQL());
    }

}
