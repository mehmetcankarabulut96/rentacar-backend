package org.example.RentACar.business.concretes;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CarService;
import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.business.responses.Car.GetByIdCarResponse;
import org.example.RentACar.business.rules.CarBusinessRules;
import org.example.RentACar.dataAccess.abstracts.CarRepository;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.enums.CarState;
import org.example.RentACar.utils.exception.BusinessException;
import org.example.RentACar.utils.mapper.Car.CarMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    CarRepository carRepository;
    CarMapperService carMapperService;
    CarBusinessRules carBusinessRules;

    @Override
    public void add(CreateCarRequest createCarRequest) {
        // validation
        carBusinessRules.checkIfCarCanBeCreated(createCarRequest);

        // mapping
        Car car = carMapperService.mapToCar(createCarRequest);
        car.setState(CarState.AVAILABLE);
        car.setDeleted(false);

        // persistence
        carRepository.save(car);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GetAllCarsResponse> getAll(Pageable pageable) {
        return carRepository.findAllByDeletedFalse(pageable)
                .map(carMapperService::mapToGetAllCarResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public GetByIdCarResponse getById(int id) {
        Car car = carRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Car not found with id: " + id));

        return carMapperService.mapToGetByIdCarResponse(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {
        Car car = carRepository.findByIdAndDeletedFalse(updateCarRequest.getId())
                .orElseThrow(() -> new BusinessException("Car not found with id: " + updateCarRequest.getId()));

        carBusinessRules.checkIfCarCanBeUpdated(updateCarRequest, car);

        carMapperService.mapToCar(updateCarRequest, car);

        carRepository.save(car);
    }

    @Override
    public void delete(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Car not found with id: " + id));

        carBusinessRules.checkIfCarCanBeDeleted(id);

        car.setDeleted(true);

        carRepository.save(car);
    }

    @Override
    public boolean existsById(int id) {
        return carRepository.existsById(id);
    }

    @Override
    public void checkIfCarCanBeRented(int id) {
        carBusinessRules.checkIfCarExists(id);

        carBusinessRules.checkIfCarStateOkForRented(id);
    }
}