package org.example.RentACar.utils.mapper.Car;

import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.entities.concretes.Car;


public interface CarMapperService {
    GetAllCarsResponse mapToGetAllCarResponse(Car car);
}