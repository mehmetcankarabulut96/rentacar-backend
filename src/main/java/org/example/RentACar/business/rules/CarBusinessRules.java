package org.example.RentACar.business.rules;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.ModelService;
import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.dataAccess.abstracts.CarRepository;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.enums.CarState;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private CarRepository carRepository;
    private RentalRepository rentalRepository;
    private ModelService modelService;

    public void checkIfCarStateOkForRented(int carId){
        if(!carRepository.existsByIdAndState(carId, CarState.AVAILABLE)){
            throw new BusinessException("Car is not available for rent");
        }
    }

    public void checkIfCarExists(int id){
        if(!carRepository.existsById(id)){
            throw new BusinessException("Car not found with id: " + id);
        }
    }

    public void checkIfCarExists(String plate){
        if(carRepository.existsByPlate(plate)){
            throw new BusinessException("Car exists with plate: " + plate);
        }
    }

    public void checkIfPlateUsedByAnotherCar(String plate, int id){
        if(carRepository.existsByPlateAndIdNot(plate, id)){
            throw new BusinessException("Plate " + plate + " already exists");
        }
    }

    public void checkIfCarCanBeDeleted(int id){
        if(rentalRepository.existsByCarIdAndStatus(id, RentalStatus.ACTIVE)){
            throw new BusinessException("Car cannot deleted because it has an active rental");
        }
    }

    public void checkIfCarCanBeCreated(CreateCarRequest request){
        this.checkIfCarExists(request.getPlate());

        modelService.checkIfModelExists(request.getModelId());

        if(request.getDailyPrice() <= 0){
            throw new BusinessException("Daily price must be greater than zero");
        }
    }

    public void checkIfCarCanBeUpdated(UpdateCarRequest request, Car car){
        validatePlateChange(request, car);

        validatePriceChange(request, car);

        validateModelYearChange(request, car);
    }

    private void validateModelYearChange(UpdateCarRequest request, Car car){
        int currentModelYear = car.getModelYear();
        int newModelYear = request.getModelYear();
        int currentYear = LocalDate.now().getYear();

        if(currentModelYear == newModelYear) return;

        if(newModelYear > currentYear){
            throw new BusinessException("Model year cannot be greater than current year");
        }

        if(currentYear - newModelYear > 10){
            throw new BusinessException("Cars older than 10 years cannot be used");
        }
    }

    private void validatePriceChange(UpdateCarRequest request, Car car){
        double currentDailyPrice = car.getDailyPrice();
        double newDailyPrice = request.getDailyPrice();

        if(currentDailyPrice == newDailyPrice) return;

        if(newDailyPrice <= 0){
            throw new BusinessException("Daily price must be greater than zero");
        }

        if(newDailyPrice > currentDailyPrice * 1.30){
            throw new BusinessException("Daily price increase rate cannot be exceed 30 percent");
        }

    }

    private void validatePlateChange(UpdateCarRequest request, Car car){
        String currentPlate = car.getPlate();
        String newPlate = request.getPlate();

        if(currentPlate.equals(newPlate)) return;

        checkIfPlateUsedByAnotherCar(newPlate, request.getId());
    }
}