package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByName(String name);
    boolean existsById(int id);
}