package org.example.RentACar.utils.mapper.Rental;

import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllActiveRentalResponse;
import org.example.RentACar.entities.concretes.Rental;

public interface RentalMapperService {
    GetAllActiveRentalResponse mapToGetAllActiveRentalResponse(Rental rental);
    void mapToRental(UpdateRentalRequest request, Rental rental);
}