package org.example.RentACar.business.abstracts;

import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;

import java.util.List;

public interface CustomerService {
    void add(CreateCustomerRequest request);
    List<GetAllCustomerResponse> getAll();
    GetByIdCustomerResponse getById(int id);
    void update(UpdateCustomerRequest request);
    void delete(int id);

    void checkIfCustomerCanRent(int customerId);
    boolean isCustomerExistsById(int id);
}