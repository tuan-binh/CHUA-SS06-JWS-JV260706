package org.example.chua_ss06_jws.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.chua_ss06_jws.models.constants.VehicleType;

@Entity
@Table(name = "vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "color")
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VehicleType type;
}
