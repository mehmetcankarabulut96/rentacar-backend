package org.example.RentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.RentACar.entities.enums.RentalStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
@Setter
@NoArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @Column(name = "start_datetime")
    private LocalDateTime startDateTime;

    @Column(name = "end_datetime")
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name="car_id", nullable = false)
    Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;
}