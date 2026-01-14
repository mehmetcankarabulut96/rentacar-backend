package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByBrandId(int brandId);
    boolean existsById(int id);
    Optional<Model> findByNameIgnoreCase(String name);
}