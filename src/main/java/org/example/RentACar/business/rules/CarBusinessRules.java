package org.example.RentACar.business.rules;

import lombok.AllArgsConstructor;
import org.example.RentACar.dataAccess.abstracts.CarRepository;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private CarRepository carRepository;
    private RentalRepository rentalRepository;

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

    public void checkIfCarHasActiveRental(int carId){
        if(rentalRepository.existsByCarIdAndStatus(carId, RentalStatus.ACTIVE)){
            throw new BusinessException("Car cannot deleted because it has an active rental");
        }
    }

    public void checkIfDeletedStatusUpdateable(int carId, boolean deleted){
        if(deleted){
            this.checkIfCarHasActiveRental(carId);
        }
        else {
            throw new BusinessException("Deleted cars cannot be re-created at this moment.");
        }
    }
}