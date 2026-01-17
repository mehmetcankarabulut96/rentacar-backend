package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    boolean existsById(int id);
    boolean existsByPlate(String plate);
    boolean existsByPlateAndIdNot(String plate, int id);
    Page<Car> findAllByDeletedFalse(Pageable pageable);
    Optional<Car> findByIdAndDeletedFalse(int id);
}