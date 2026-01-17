package org.example.RentACar.business.rules;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CarService;
import org.example.RentACar.business.abstracts.CustomerService;
import org.example.RentACar.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    CarService carService;
    CustomerService customerService;

    public void checkIfRentalCreateable(int carId, int customerId){
        if(!carService.isCarExistsById(carId)){
            throw new BusinessException("Car not found with id: " + carId);
        }
        if(!customerService.isCustomerExistsById(customerId)){
            throw new BusinessException("Customer not found with id: " + customerId);
        }
    }
}