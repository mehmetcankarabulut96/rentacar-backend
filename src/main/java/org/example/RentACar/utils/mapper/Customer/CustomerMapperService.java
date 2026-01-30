package org.example.RentACar.utils.mapper.Customer;

import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.concretes.Rental;

import java.util.List;

public interface CustomerMapperService {
    Customer toCustomer(CreateCustomerRequest request);
    GetByIdCustomerResponse toGetByIdCustomerResponse(Customer customer, List<Rental> activeRentals, List<Rental> recentRentals);
    GetAllCustomerResponse toGetAllCustomerResponse(Customer customer);
    void updateCustomer(UpdateCustomerRequest request, Customer customer);
}