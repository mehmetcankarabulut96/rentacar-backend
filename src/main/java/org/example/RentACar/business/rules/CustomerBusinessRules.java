package org.example.RentACar.business.rules;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.dataAccess.abstracts.CustomerRepository;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private CustomerRepository customerRepository;
    private RentalRepository rentalRepository;


    public void checkIfCustomerCanBeDeleted(int customerId){
        checkIfCustomerHasActiveRental(customerId);
    }

    private void checkIfEmailUsedByAnotherCustomer(int customerId, String email){
        if(customerRepository.existsByEmailAndIdNot(email, customerId)){
            throw new BusinessException("Email used by another customer: " + email);
        }
    }

    public void checkIfCustomerCanBeUpdated(UpdateCustomerRequest request, Customer customer){
        int customerId = request.getId();
        String currentEmail = customer.getEmail();
        String newEmail = request.getEmail();

        if(!currentEmail.equals(newEmail)){
            checkIfEmailUsedByAnotherCustomer(customerId, newEmail);
        }
    }

    public void checkIfCustomerCanBeCreated(CreateCustomerRequest request){
        checkIfCustomerExists(request.getEmail());
    }

    public void checkIfCustomerExists(int id){
        if(!customerRepository.existsById(id)){
            throw new BusinessException("Customer not found with id: " + id);
        }
    }

    public void checkIfCustomerExists(String email){
        if(customerRepository.existsByEmail(email)){
            throw new BusinessException("Customer already exists with email: " + email);
        }
    }

    public void checkIfCustomerHasActiveRental(int customerId){
        if(rentalRepository.existsByCustomerIdAndStatus(customerId, RentalStatus.ACTIVE)){
            throw new BusinessException("Customer has active rental");
        }
    }
}