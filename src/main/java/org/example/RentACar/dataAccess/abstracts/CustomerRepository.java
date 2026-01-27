package org.example.RentACar.dataAccess.abstracts;

import org.example.RentACar.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsById(int id);
    boolean existsByEmail(String email);
    List<Customer> findAllByDeletedFalse();
    Optional<Customer> findByIdAndDeletedFalse(int id);

    boolean existsByEmailAndIdNot(String email, int customerId);
}