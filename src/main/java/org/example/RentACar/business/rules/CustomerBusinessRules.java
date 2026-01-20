package org.example.RentACar.business.rules;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.RentalService;
import org.example.RentACar.dataAccess.abstracts.CustomerRepository;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private CustomerRepository customerRepository;
    private RentalService rentalService;

    public void checkIfCustomerExists(String email){
        if(customerRepository.existsByEmail(email)){
            throw new BusinessException("Customer already exists with email: " + email);
        }
    }

    public void checkIfCustomerHasActiveRental(int customerId){
        if(rentalService.existsByCustomerIdAndStatus(customerId, RentalStatus.ACTIVE)){
            throw new BusinessException("Customer can not deleted because it has active rental");
        }
    }
}