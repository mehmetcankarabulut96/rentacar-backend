package org.example.RentACar.utils.mapper.Customer;

import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;
import org.example.RentACar.business.responses.Rental.GetActiveRentalsByCustomerResponse;
import org.example.RentACar.business.responses.Rental.GetRecentRentalsByCustomerResponse;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;

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
        response.setActiveRentals(customer.getRentals().stream().
                filter(rental -> rental.getStatus() == RentalStatus.ACTIVE)
                .map(rental -> new GetActiveRentalsByCustomerResponse(
                            rental.getId(),
                            rental.getStartDateTime(),
                            rental.getCar().getPlate()
                        ))
                .toList()
        );
        response.setRecentRentals(customer.getRentals()
                .stream()
                .filter(rental -> rental.getEndDateTime() != null && rental.getStatus() == RentalStatus.COMPLETED)
                .sorted(Comparator.comparing(Rental::getEndDateTime).reversed())
                .limit(10)
                .map(rental -> new GetRecentRentalsByCustomerResponse(
                        rental.getId(),
                        rental.getStartDateTime(),
                        rental.getEndDateTime(),
                        rental.getCar().getPlate()
                ))
                .toList()
        );

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
