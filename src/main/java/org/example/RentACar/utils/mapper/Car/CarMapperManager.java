package org.example.RentACar.utils.mapper.Car;

import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.business.responses.Car.GetByIdCarResponse;
import org.example.RentACar.business.responses.Customer.GetCustomerByActiveRentalResponse;
import org.example.RentACar.business.responses.Rental.GetActiveRentalByCarResponse;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.concretes.Model;
import org.example.RentACar.entities.enums.RentalStatus;

public class CarMapperManager implements CarMapperService{
    @Override
    public GetAllCarsResponse mapToGetAllCarResponse(Car car) {
        GetAllCarsResponse response = new GetAllCarsResponse();
        response.setId(car.getId());
        response.setPlate(car.getPlate());
        response.setDailyPrice(car.getDailyPrice());
        response.setModelYear(car.getModelYear());
        response.setState(car.getState().name());
        response.setModelName(car.getModel().getName());

        return response;
    }

    @Override
    public GetByIdCarResponse mapToGetByIdCarResponse(Car car) {
        GetByIdCarResponse response = new GetByIdCarResponse();
        response.setId(car.getId());
        response.setPlate(car.getPlate());
        response.setDailyPrice(car.getDailyPrice());
        response.setModelYear(car.getModelYear());
        response.setState(car.getState().name());
        response.setModelName(car.getModel().getName());
        response.setActiveRental(car.getRentals().stream()
                .filter(rental -> rental.getStatus() == RentalStatus.ACTIVE)
                .map(rental -> new GetActiveRentalByCarResponse(
                            rental.getId(),
                            rental.getStartDateTime(),
                            new GetCustomerByActiveRentalResponse(
                                    rental.getCustomer().getId(),
                                    rental.getCustomer().getFirstName(),
                                    rental.getCustomer().getLastName(),
                                    rental.getCustomer().getEmail()
                            )
                        )
                ).findAny().orElse(null));

        return response;
    }

    @Override
    public Car mapToCar(CreateCarRequest createCarRequest) {
        Car car = new Car();
        car.setPlate(createCarRequest.getPlate());
        car.setDailyPrice(createCarRequest.getDailyPrice());
        car.setModelYear(createCarRequest.getModelYear());

        Model model = new Model();
        model.setId(createCarRequest.getModelId());
        car.setModel(model);

        return car;
    }

    @Override
    public void mapToCar(UpdateCarRequest createCarRequest, Car car) {
        car.setPlate(createCarRequest.getPlate());
        car.setDailyPrice(createCarRequest.getDailyPrice());
        car.setModelYear(createCarRequest.getModelYear());
    }
}