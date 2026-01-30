package org.example.RentACar.utils.mapper.Customer;

import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;
import org.example.RentACar.business.responses.Rental.GetActiveRentalsByCustomerResponse;
import org.example.RentACar.business.responses.Rental.GetRecentRentalsByCustomerResponse;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerMapperManager implements CustomerMapperService{

    @Override
    public Customer toCustomer(CreateCustomerRequest request) {

        if(request == null) return null;

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());

        return customer;
    }

    private String getCarPlate(Car car){
        return (car == null)? null: car.getPlate();
    }

    private GetActiveRentalsByCustomerResponse toGetActiveRentalsResponse(Rental rental){

        if(rental == null) return null;

        GetActiveRentalsByCustomerResponse response = new GetActiveRentalsByCustomerResponse();
        response.setId(rental.getId());
        response.setStartDateTime(rental.getStartDateTime());
        response.setCarPlate(getCarPlate(rental.getCar()));

        return response;
    }

    private List<GetActiveRentalsByCustomerResponse> toGetActiveRentalsResponseList(List<Rental> rentals){

        if(rentals == null) return List.of();

        return rentals
                .stream()
                .map(this::toGetActiveRentalsResponse)
                .toList();
    }

    private GetRecentRentalsByCustomerResponse GetRecentRentalsResponse(Rental rental){

        if(rental == null) return null;

        GetRecentRentalsByCustomerResponse response = new GetRecentRentalsByCustomerResponse();
        response.setRentalId(rental.getId());
        response.setStartDateTime(rental.getStartDateTime());
        response.setEndDateTime(rental.getEndDateTime());
        response.setCarPlate(getCarPlate(rental.getCar()));

        return response;
    }

    private List<GetRecentRentalsByCustomerResponse> toGetRecentRentalsResponseList(List<Rental> rentals){

        if(rentals == null) return List.of();

        return rentals
                .stream()
                .map(this::GetRecentRentalsResponse)
                .toList();
    }

    @Override
    public GetByIdCustomerResponse toGetByIdCustomerResponse(Customer customer, List<Rental> activeRentals, List<Rental> recentRentals) {

        if(customer == null) return null;

        GetByIdCustomerResponse response = new GetByIdCustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setActiveRentals(toGetActiveRentalsResponseList(activeRentals));
        response.setRecentRentals(toGetRecentRentalsResponseList(recentRentals));

        return response;
    }

    @Override
    public GetAllCustomerResponse toGetAllCustomerResponse(Customer customer) {

        if(customer == null) return null;

        GetAllCustomerResponse response = new GetAllCustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());

        return response;
    }

    @Override
    public void updateCustomer(UpdateCustomerRequest request, Customer customer) {

        if(request == null || customer == null) return;

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
    }
}