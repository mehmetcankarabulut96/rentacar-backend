package org.example.RentACar.utils.mapper.Customer;

import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;
import org.example.RentACar.entities.concretes.Customer;

public interface CustomerMapperService {
    Customer from(CreateCustomerRequest request);
    GetByIdCustomerResponse toGetByIdCustomerResponse(Customer customer);
    GetAllCustomerResponse toGetAllCustomerResponse(Customer customer);
    void mapToCustomer(UpdateCustomerRequest request, Customer customer);
}