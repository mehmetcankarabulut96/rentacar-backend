package org.example.RentACar.business.concretes;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CustomerService;
import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;
import org.example.RentACar.business.rules.CustomerBusinessRules;
import org.example.RentACar.dataAccess.abstracts.CustomerRepository;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.exception.BusinessException;
import org.example.RentACar.utils.mapper.Customer.CustomerMapperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerBusinessRules customerBusinessRules;
    private CustomerMapperService customerMapperService;

    private RentalRepository rentalRepository;

    @Override
    public void add(CreateCustomerRequest request) {
        customerBusinessRules.checkIfCustomerCanBeCreated(request);

        Customer customer = customerMapperService.toCustomer(request);
        customer.setDeleted(false);

        customerRepository.save(customer);
    }

    @Override
    public List<GetAllCustomerResponse> getAll() {
        return customerRepository.findAllByDeletedFalse()
                .stream()
                .map(customerMapperService::toGetAllCustomerResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GetByIdCustomerResponse getById(int id) {
        Customer customer = customerRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));

        List<Rental> activeRentals = rentalRepository.findAllByCustomerIdAndStatus(customer.getId(), RentalStatus.ACTIVE)
                .orElse(null);

        List<Rental> recentRentals = rentalRepository.findTop10ByCustomerIdAndStatusOrderByEndDateTimeDesc(id, RentalStatus.COMPLETED)
                .orElse(null);

        GetByIdCustomerResponse response = customerMapperService.toGetByIdCustomerResponse(customer, activeRentals, recentRentals);
        response.setRentalCount(rentalRepository.countByCustomerId(id));

        return response;
    }

    @Override
    public void update(UpdateCustomerRequest request) {
        Customer customer = customerRepository.findByIdAndDeletedFalse(request.getId())
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + request.getId()));

        customerBusinessRules.checkIfCustomerCanBeUpdated(request, customer);

        customerMapperService.updateCustomer(request, customer);

        customerRepository.save(customer);
    }

    @Override
    public void delete(int id) {
        Customer customer = customerRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));

        customerBusinessRules.checkIfCustomerCanBeDeleted(id);

        customer.setDeleted(true);

        customerRepository.save(customer);
    }

    @Override
    public void checkIfCustomerCanRent(int customerId) {
        customerBusinessRules.checkIfCustomerExists(customerId);

        customerBusinessRules.checkIfCustomerHasActiveRental(customerId);
    }

    @Override
    public boolean isCustomerExistsById(int id) {
        return customerRepository.existsById(id);
    }
}