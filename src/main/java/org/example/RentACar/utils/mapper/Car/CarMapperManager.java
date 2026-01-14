package org.example.RentACar.utils.mapper.Car;

import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.business.responses.Car.GetByIdCarResponse;
import org.example.RentACar.entities.concretes.Car;

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

        return response;
    }

    @Override
    public Car mapToCar(CreateCarRequest createCarRequest) {
        Car car = new Car();
        car.setPlate(createCarRequest.getPlate());
        car.setDailyPrice(createCarRequest.getDailyPrice());
        car.setModelYear(createCarRequest.getModelYear());

        return car;
    }

    @Override
    public void mapToCar(UpdateCarRequest createCarRequest, Car car) {
        car.setPlate(createCarRequest.getPlate());
        car.setDailyPrice(createCarRequest.getDailyPrice());
        car.setModelYear(createCarRequest.getModelYear());
        car.setState(createCarRequest.getState());
    }
}