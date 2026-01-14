package org.example.RentACar.business.rules;

import lombok.AllArgsConstructor;
import org.example.RentACar.dataAccess.abstracts.CarRepository;
import org.example.RentACar.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private CarRepository carRepository;

    public void checkIfCarExists(String plate){
        if(carRepository.existsByPlate(plate)){
            throw new BusinessException("Car exists with plate: " + plate);
        }
    }

    public void checkIfPlateUsedByAnotherCar(String plate, int id){
        if(carRepository.existsByPlateAndIdNot(plate, id)){
            throw new BusinessException("Plate already exists");
        }
    }
}
