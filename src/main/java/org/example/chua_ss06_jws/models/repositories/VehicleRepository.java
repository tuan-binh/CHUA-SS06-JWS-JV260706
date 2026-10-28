package org.example.chua_ss06_jws.models.repositories;

import org.example.chua_ss06_jws.models.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
