package org.example.RentACar.business.abstracts;

import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllActiveRentalResponse;
import org.example.RentACar.entities.enums.RentalStatus;

import java.util.List;

public interface RentalService {
    void add(CreateRentalRequest request);
    List<GetAllActiveRentalResponse> getAllActive();
    void update(UpdateRentalRequest request);
    void delete(int id);
    boolean existsByCustomerIdAndStatus(int customerId, RentalStatus status);
}