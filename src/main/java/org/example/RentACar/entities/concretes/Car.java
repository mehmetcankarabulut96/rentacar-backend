package org.example.RentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.RentACar.entities.enums.CarState;

import java.util.List;

@Table(name="cars")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "plate", unique = true)
    private String plate;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private CarState state;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
}