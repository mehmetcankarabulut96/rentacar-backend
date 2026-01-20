package org.example.RentACar.business.concretes;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CustomerService;
import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;
import org.example.RentACar.business.rules.CustomerBusinessRules;
import org.example.RentACar.dataAccess.abstracts.CustomerRepository;
import org.example.RentACar.entities.concretes.Customer;
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

    @Override
    public void add(CreateCustomerRequest request) {
        customerBusinessRules.checkIfCustomerExists(request.getEmail());

        Customer customer = customerMapperService.from(request);
        customer.setDeleted(false);

        customerRepository.save(customer);
    }

    @Override
    public List<GetAllCustomerResponse> getAllByDeletedFalse() {
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

        GetByIdCustomerResponse response = customerMapperService.toGetByIdCustomerResponse(customer);
        response.setRentalCount(customer.getRentals().size()); // lazy

        return response;
    }

    @Override
    public void update(UpdateCustomerRequest request) {
        Customer customer = customerRepository.findByIdAndDeletedFalse(request.getId())
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + request.getId()));

        customerMapperService.mapToCustomer(request, customer);

        customerRepository.save(customer);
    }

    @Override
    public void delete(int id) {
        Customer customer = customerRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));

        customerBusinessRules.checkIfCustomerHasActiveRental(id);

        customer.setDeleted(true);

        customerRepository.save(customer);
    }

    @Override
    public boolean isCustomerExistsById(int id) {
        return customerRepository.existsById(id);
    }
}