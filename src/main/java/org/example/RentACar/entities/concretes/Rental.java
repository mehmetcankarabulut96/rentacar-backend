package org.example.RentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.RentACar.entities.enums.RentalStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
@Data
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

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name="car_id", nullable = false)
    Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;
}