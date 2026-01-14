package org.example.RentACar.utils.mapper.Car;

import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.business.responses.Car.GetByIdCarResponse;
import org.example.RentACar.entities.concretes.Car;


public interface CarMapperService {
    GetAllCarsResponse mapToGetAllCarResponse(Car car);
    GetByIdCarResponse mapToGetByIdCarResponse(Car car);
    Car mapToCar(CreateCarRequest createCarRequest);
    void mapToCar(UpdateCarRequest createCarRequest, Car car);
}