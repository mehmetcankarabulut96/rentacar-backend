package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    boolean existsByCarIdAndStatus(int carId, RentalStatus status);
}