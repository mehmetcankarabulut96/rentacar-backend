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

    @Override
    public GetAllRentalResponse mapToGetAllRentalResponse(Rental rental) {
        GetAllRentalResponse response = new GetAllRentalResponse();
        response.setId(rental.getId());
        response.setStatus(rental.getStatus());
        response.setStartDateTime(rental.getStartDateTime());
        response.setEndDateTime(rental.getEndDateTime());
        response.setCarId(rental.getCar().getId());
        response.setCustomerId(rental.getCustomer().getId());

        return response;
    }

    @Override
    public void mapToRental(UpdateRentalRequest request, Rental rental) {
        rental.setStartDateTime(request.getStartDateTime());
        rental.setEndDateTime(request.getEndDateTime());
    }

    @Override
    public Rental mapToRental(CreateRentalRequest request) {
        Rental rental = new Rental();

        Car car = new Car();
        car.setId(request.getCarId());
        rental.setCar(car);

        Customer customer = new Customer();
        customer.setId(request.getCustomerId());
        rental.setCustomer(customer);

        return rental;
    }

    @Override
    public GetByIdRentalResponse mapToGetByIdRentalResponse(Rental rental) {
        GetByIdRentalResponse response = new GetByIdRentalResponse();
        response.setId(rental.getId());
        response.setStatus(rental.getStatus());
        response.setStartDateTime(rental.getStartDateTime());
        response.setEndDateTime(rental.getEndDateTime());
        response.setCarId(rental.getCar().getId());
        response.setCustomerId(rental.getCustomer().getId());

        return response;
    }
}