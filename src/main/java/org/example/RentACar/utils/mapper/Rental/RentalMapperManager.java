package org.example.RentACar.utils.mapper.Rental;

import lombok.NoArgsConstructor;
import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllRentalResponse;
import org.example.RentACar.business.responses.Rental.GetByIdRentalResponse;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class RentalMapperManager implements RentalMapperService{

    private int getCarId(Car car){

        return (car == null)? 0: car.getId();
    }

    private int getCustomerId(Customer customer){
        return (customer == null)? 0: customer.getId();
    }

    @Override
    public GetAllRentalResponse toGetAllRentalResponse(Rental rental) {

        if(rental == null) return null;

        GetAllRentalResponse response = new GetAllRentalResponse();
        response.setId(rental.getId());
        response.setStatus(rental.getStatus());
        response.setStartDateTime(rental.getStartDateTime());
        response.setEndDateTime(rental.getEndDateTime());
        response.setCarId(getCarId(rental.getCar()));
        response.setCustomerId(getCustomerId(rental.getCustomer()));

        return response;
    }

    @Override
    public void updateRental(UpdateRentalRequest request, Rental rental) {

        if(request == null || rental == null) return;

        rental.setStartDateTime(request.getStartDateTime());
        rental.setEndDateTime(request.getEndDateTime());
    }

    private Car toCar(Integer carId){

        if(carId == null) return null;

        Car car = new Car();
        car.setId(carId);

        return car;
    }

    private Customer toCustomer(Integer customerId){

        if(customerId == null) return null;

        Customer customer = new Customer();
        customer.setId(customerId);

        return customer;
    }

    @Override
    public Rental toRental(CreateRentalRequest request) {

        if(request == null) return null;

        Rental rental = new Rental();
        rental.setCar(toCar(request.getCarId()));
        rental.setCustomer(toCustomer(request.getCustomerId()));

        return rental;
    }

    @Override
    public GetByIdRentalResponse toGetByIdRentalResponse(Rental rental) {

        if(rental == null) return null;

        GetByIdRentalResponse response = new GetByIdRentalResponse();
        response.setId(rental.getId());
        response.setStatus(rental.getStatus());
        response.setStartDateTime(rental.getStartDateTime());
        response.setEndDateTime(rental.getEndDateTime());
        response.setCarId(getCarId(rental.getCar()));
        response.setCustomerId(getCustomerId(rental.getCustomer()));

        return response;
    }
}