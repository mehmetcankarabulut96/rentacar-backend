package org.example.RentACar.business.concretes;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CarService;
import org.example.RentACar.business.abstracts.RentalService;
import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.rules.RentalBusinessRules;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.mapper.Rental.RentalMapperService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private RentalRepository rentalRepository;
    private RentalBusinessRules rentalBusinessRules;
    private EntityManager entityManager;

    @Override
    public void add(CreateRentalRequest request) {
        int carId = request.getCarId();
        int customerId = request.getCustomerId();

        rentalBusinessRules.checkIfRentalCreateable(carId, customerId);

        Rental rental = new Rental();
        rental.setStatus(RentalStatus.ACTIVE);
        rental.setStartDateTime(LocalDateTime.now());
        rental.setCar(entityManager.getReference(Car.class, carId));
        rental.setCustomer(entityManager.getReference(Customer.class, customerId));

        rentalRepository.save(rental);
    }
}