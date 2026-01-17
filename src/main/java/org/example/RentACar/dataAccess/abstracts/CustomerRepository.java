package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsById(int id);
}
