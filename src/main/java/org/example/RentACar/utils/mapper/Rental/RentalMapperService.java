package org.example.RentACar.utils.mapper.Rental;

import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllRentalResponse;
import org.example.RentACar.business.responses.Rental.GetByIdRentalResponse;
import org.example.RentACar.entities.concretes.Rental;

public interface RentalMapperService {
    GetAllRentalResponse toGetAllRentalResponse(Rental rental);
    void updateRental(UpdateRentalRequest request, Rental rental);
    Rental toRental(CreateRentalRequest request);
    GetByIdRentalResponse toGetByIdRentalResponse(Rental rental);
}