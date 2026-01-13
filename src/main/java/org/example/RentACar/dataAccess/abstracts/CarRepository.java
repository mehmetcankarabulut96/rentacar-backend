package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}