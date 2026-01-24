package org.example.RentACar.business.concretes;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.RentalService;
import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllRentalResponse;
import org.example.RentACar.business.responses.Rental.GetByIdRentalResponse;
import org.example.RentACar.business.rules.RentalBusinessRules;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
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
    private RentalMapperService rentalMapperService;

    @Override
    public void add(CreateRentalRequest request) {

        rentalBusinessRules.checkIfRentalCanBeCreated(request);

        Rental rental = rentalMapperService.mapToRental(request);
        rental.setStatus(RentalStatus.ACTIVE);
        rental.setStartDateTime(LocalDateTime.now());
        rental.setDeleted(false);

        rentalRepository.save(rental);
    }

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAllByDeletedFalse();
        return rentals.stream().map(rentalMapperService::mapToGetAllRentalResponse).toList();
    }

    @Override
    public GetByIdRentalResponse getById(int id) {
        Rental rental = rentalRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Rental not found with id: " + id));

        return rentalMapperService.mapToGetByIdRentalResponse(rental);
    }

    @Override
    public void update(UpdateRentalRequest request) {

        Rental rental = rentalRepository.findById(request.getId())
                .orElseThrow(() -> new BusinessException("Rental not found with id: " + request.getId()));

        rentalBusinessRules.checkIfRentalCanBeUpdated(request, rental);

        rentalMapperService.mapToRental(request, rental);

        rentalRepository.save(rental);
    }

    @Override
    public void delete(int id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Rental not found with id: " + id));

        rentalBusinessRules.checkIfRentalCanBeDeleted(rental);

        rental.setDeleted(true);

        rentalRepository.save(rental);
    }

    @Override
    public boolean existsByCustomerIdAndStatus(int customerId, RentalStatus status) {
        return rentalRepository.existsByCustomerIdAndStatus(customerId, status);
    }
}