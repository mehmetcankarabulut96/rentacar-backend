package org.example.RentACar.business.rules;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CarService;
import org.example.RentACar.business.abstracts.CustomerService;
import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    RentalRepository rentalRepository;

    CarService carService;
    CustomerService customerService;


    public void checkIfRentalCanBeCreated(CreateRentalRequest request){

        carService.checkIfCarCanBeRented(request.getCarId());

        customerService.checkIfCustomerCanRent(request.getCustomerId());
    }

    private void validateTimeUpdate(LocalDateTime newStartTime, LocalDateTime currentStartTime, LocalDateTime newEndTime, LocalDateTime currentEndTime){
        if(currentEndTime == null && newEndTime != null){
            throw new BusinessException("End time cannot be updated because current end time is null");
        }

        if(currentEndTime != null && newEndTime == null){
            throw new BusinessException("End time cannot be null because current end time is not null");
        }

        if(newEndTime != null){
            if(!newStartTime.isBefore(newEndTime)){
                throw new BusinessException("Start time must be before end time");
            }

            if(!newStartTime.isBefore(currentEndTime)){
                throw new BusinessException("New start time cannot be after or equal to current end time");
            }

            if (!newEndTime.isAfter(currentStartTime)) {
                throw new BusinessException("New end time cannot be before or equal to current start time");
            }
        }
    }

    public void checkIfRentalCanBeUpdated(UpdateRentalRequest request, Rental rental){
        validateTimeUpdate(request.getStartDateTime(), rental.getStartDateTime(), request.getEndDateTime(), rental.getEndDateTime());
    }

    public void checkIfRentalCanBeDeleted(Rental rental){
        if(rental.getStatus() == RentalStatus.ACTIVE){
            throw new BusinessException("Active rentals can not be deleted");
        }
    }
}