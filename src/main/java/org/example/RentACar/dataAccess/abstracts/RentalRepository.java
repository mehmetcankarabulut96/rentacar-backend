package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    boolean existsByCustomerIdAndStatus(int customerId, RentalStatus status);
    boolean existsByCarIdAndStatus(int carId, RentalStatus status);

    List<Rental> findAllByDeletedFalse();
    Optional<Rental> findByIdAndDeletedFalse(int id);

    Optional<Rental> findByCarIdAndStatus(int id, RentalStatus rentalStatus);
}