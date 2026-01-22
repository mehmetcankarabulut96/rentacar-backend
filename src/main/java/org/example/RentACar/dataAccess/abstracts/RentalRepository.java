package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findAllByStatus(RentalStatus status);
    boolean existsByIdAndStatus(int id, RentalStatus status);
    boolean existsByCustomerIdAndStatus(int customerId, RentalStatus status);

    boolean existsByCarIdAndStatus(int carId, RentalStatus status);
}