package org.example.RentACar.business.rules;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CarService;
import org.example.RentACar.business.abstracts.CustomerService;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    CarService carService;
    RentalRepository rentalRepository;

    public void checkIfRentalCreateable(int carId, int customerId){
        if(!carService.isCarExistsById(carId)){
            throw new BusinessException("Car not found with id: " + carId);
        }
    }

    public void checkIfStatusCanChange(RentalStatus newStatus, RentalStatus oldStatus){
        if(newStatus == RentalStatus.ACTIVE && oldStatus == RentalStatus.COMPLETED){
            throw new BusinessException("Status change from COMPLETED to ACTIVE is not allowed");
        }
    }

    public void checkIfEndDateTimeCanChange(LocalDateTime newEndDateTime, RentalStatus oldStatus, LocalDateTime startDateTime){
        if(newEndDateTime == null && oldStatus == RentalStatus.COMPLETED){
            throw new BusinessException("Endtime can not be null with COMPLETED status");
        }
        if(newEndDateTime != null && newEndDateTime.isBefore(startDateTime)){
            throw new BusinessException("End time must be greater than start time");
        }
    }

    public void checkIfDeletedStatusCanChange(int id, boolean deleted){
        if(deleted && rentalRepository.existsByIdAndStatus(id, RentalStatus.ACTIVE)){
            throw new BusinessException("Active rentals can not be deleted");
        }
        if(!deleted && rentalRepository.existsByIdAndStatus(id, RentalStatus.COMPLETED)){
            throw new BusinessException("Completed rentals can not be undelete");
        }
    }

    public void checkIfRentalCanDelete(RentalStatus status){
        if(status == RentalStatus.ACTIVE){
            throw new BusinessException("Active rentals can not be deleted");
        }
    }
}