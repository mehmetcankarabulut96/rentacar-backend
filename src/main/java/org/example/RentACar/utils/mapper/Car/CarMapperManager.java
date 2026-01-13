package org.example.RentACar.utils.mapper.Car;

import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
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
}
