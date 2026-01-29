package org.example.RentACar.utils.mapper.Car;

import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.business.responses.Car.GetByIdCarResponse;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.concretes.Rental;


public interface CarMapperService {
    GetAllCarsResponse toGetAllCarsResponse(Car car);
    GetByIdCarResponse toGetByIdResponse(Car car, Rental activeRental);
    Car toCar(CreateCarRequest request);
    void updateCar(UpdateCarRequest request, Car car);
}