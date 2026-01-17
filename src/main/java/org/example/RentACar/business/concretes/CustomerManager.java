package org.example.RentACar.business.concretes;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CustomerService;
import org.example.RentACar.dataAccess.abstracts.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public boolean isCustomerExistsById(int id) {
        return customerRepository.existsById(id);
    }
}
