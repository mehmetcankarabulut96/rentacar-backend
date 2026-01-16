package org.example.RentACar.entities.concretes;

import jakarta.persistence.*;
import org.example.RentACar.entities.enums.RentalStatus;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @ManyToOne
    @JoinColumn(name="car_id", nullable = false)
    Car car;
}