package org.example.RentACar.business.concretes;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.RentalService;
import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllActiveRentalResponse;
import org.example.RentACar.business.rules.RentalBusinessRules;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.concretes.Customer;
import org.example.RentACar.entities.concretes.Rental;
import org.example.RentACar.entities.enums.RentalStatus;
import org.example.RentACar.utils.exception.BusinessException;
import org.example.RentACar.utils.mapper.Rental.RentalMapperService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private RentalRepository rentalRepository;
    private RentalBusinessRules rentalBusinessRules;
    private EntityManager entityManager;
    private RentalMapperService rentalMapperService;

    @Override
    public void add(CreateRentalRequest request) {
        int carId = request.getCarId();
        int customerId = request.getCustomerId();

        rentalBusinessRules.checkIfRentalCreateable(carId, customerId);

        Rental rental = new Rental();
        rental.setStatus(RentalStatus.ACTIVE);
        rental.setStartDateTime(LocalDateTime.now());
        rental.setDeleted(false);
        rental.setCar(entityManager.getReference(Car.class, carId));
        rental.setCustomer(entityManager.getReference(Customer.class, customerId));

        rentalRepository.save(rental);
    }

    @Override
    public List<GetAllActiveRentalResponse> getAllActive() {
        List<Rental> rentals = rentalRepository.findAllByStatus(RentalStatus.ACTIVE);
        return rentals.stream().map(rentalMapperService::mapToGetAllActiveRentalResponse).toList();
    }

    @Override
    public void update(UpdateRentalRequest request) {
        int id = request.getId();

        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Rental not found with id: " + id));

        rentalBusinessRules.checkIfStatusCanChange(request.getStatus(), rental.getStatus());

        rentalBusinessRules.checkIfEndDateTimeCanChange(request.getEndDateTime(), request.getStatus(), rental.getStartDateTime());

        rentalBusinessRules.checkIfDeletedStatusCanChange(id, request.isDeleted());

        rentalMapperService.mapToRental(request, rental);

        rentalRepository.save(rental);
    }

    @Override
    public void delete(int id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Rental not found with id: " + id));

        rentalBusinessRules.checkIfRentalCanDelete(rental.getStatus());

        rental.setEndDateTime(LocalDateTime.now());
        rental.setDeleted(true);

        rentalRepository.save(rental);
    }
}