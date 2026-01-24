package org.example.RentACar.utils.mapper.Rental;

import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllRentalResponse;
import org.example.RentACar.business.responses.Rental.GetByIdRentalResponse;
import org.example.RentACar.entities.concretes.Rental;

public interface RentalMapperService {
    GetAllRentalResponse mapToGetAllRentalResponse(Rental rental);
    void mapToRental(UpdateRentalRequest request, Rental rental);
    Rental mapToRental(CreateRentalRequest request);
    GetByIdRentalResponse mapToGetByIdRentalResponse(Rental rental);
}