package org.example.RentACar.business.concretes;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CarService;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.dataAccess.abstracts.CarRepository;
import org.example.RentACar.utils.mapper.Car.CarMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    CarRepository carRepository;
    CarMapperService carMapperService;

    @Override
    public Page<GetAllCarsResponse> getAll(Pageable pageable) {

        return carRepository.findAll(pageable)
                .map(carMapperService::mapToGetAllCarResponse);
    }
}