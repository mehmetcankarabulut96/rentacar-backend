package org.example.RentACar.utils.mapper.Customer;

import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;
import org.example.RentACar.entities.concretes.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapperManager implements CustomerMapperService{
    @Override
    public Customer from(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());

        return customer;
    }

    @Override
    public GetByIdCustomerResponse toGetByIdCustomerResponse(Customer customer) {
        GetByIdCustomerResponse response = new GetByIdCustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());

        return response;
    }

    @Override
    public GetAllCustomerResponse toGetAllCustomerResponse(Customer customer) {
        GetAllCustomerResponse response = new GetAllCustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());

        return response;
    }

    @Override
    public void mapToCustomer(UpdateCustomerRequest request, Customer customer) {
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
    }
}
