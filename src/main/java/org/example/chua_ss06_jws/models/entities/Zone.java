package org.example.chua_ss06_jws.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "occupied_spots")
    private Integer occupiedSpots;

}
