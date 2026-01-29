package org.example.RentACar.utils.mapper.Car;

import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.business.responses.Car.GetByIdCarResponse;
import org.example.RentACar.business.responses.Customer.GetCustomerByActiveRentalResponse;
import org.example.RentACar.business.responses.Rental.GetActiveRentalByCarResponse;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.concretes.Model;
import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.springframework.stereotype.Service;

@Service
public class CarMapperManager implements CarMapperService{

    private String getModelName(Model model){
        return (model != null)? model.getName(): null;
    }

    @Override
    public GetAllCarsResponse toGetAllCarsResponse(Car car) {

        if(car == null) return null;

        GetAllCarsResponse response = new GetAllCarsResponse();
        response.setId(car.getId());
        response.setPlate(car.getPlate());
        response.setDailyPrice(car.getDailyPrice());
        response.setModelYear(car.getModelYear());
        response.setState(car.getState().name());
        response.setModelName(getModelName(car.getModel()));

        return response;
    }

    private GetCustomerByActiveRentalResponse toGetByRentalResponse(Customer customer){

        if(customer == null) return null;

        GetCustomerByActiveRentalResponse response = new GetCustomerByActiveRentalResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());

        return response;
    }

    private GetActiveRentalByCarResponse toGetByCarResponse(Rental rental){

        if(rental == null) return null;

        GetActiveRentalByCarResponse response = new GetActiveRentalByCarResponse();
        response.setId(rental.getId());
        response.setStartDateTime(rental.getStartDateTime());
        response.setCustomer(toGetByRentalResponse(rental.getCustomer()));

        return response;
    }

    @Override
    public GetByIdCarResponse toGetByIdResponse(Car car, Rental activeRental) {

        if(car == null) return null;

        GetByIdCarResponse response = new GetByIdCarResponse();
        response.setId(car.getId());
        response.setPlate(car.getPlate());
        response.setDailyPrice(car.getDailyPrice());
        response.setModelYear(car.getModelYear());
        response.setState(car.getState().name());
        response.setModelName(getModelName(car.getModel()));
        response.setActiveRental(toGetByCarResponse(activeRental));

        return response;
    }

    private Model toModel(Integer modelId){

        if(modelId == null) return null;

        Model model = new Model();
        model.setId(modelId);

        return model;
    }

    @Override
    public Car toCar(CreateCarRequest request) {

        if(request == null) return null;

        Car car = new Car();
        car.setPlate(request.getPlate());
        car.setDailyPrice(request.getDailyPrice());
        car.setModelYear(request.getModelYear());
        car.setModel(toModel(request.getModelId()));

        return car;
    }

    @Override
    public void updateCar(UpdateCarRequest request, Car car) {

        if(request == null || car == null) return;

        car.setPlate(request.getPlate());
        car.setDailyPrice(request.getDailyPrice());
        car.setModelYear(request.getModelYear());
    }
}