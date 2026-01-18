package org.example.RentACar.utils.mapper.Rental;

import lombok.NoArgsConstructor;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllActiveRentalResponse;
import org.example.RentACar.entities.concretes.Rental;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class RentalMapperManager implements RentalMapperService{

    @Override
    public GetAllActiveRentalResponse mapToGetAllActiveRentalResponse(Rental rental) {
        GetAllActiveRentalResponse response = new GetAllActiveRentalResponse();
        response.setId(rental.getId());
        response.setStartDateTime(rental.getStartDateTime());
        response.setCarId(rental.getCar().getId());
        response.setCustomerId(rental.getCustomer().getId());

        return response;
    }

    @Override
    public void mapToRental(UpdateRentalRequest request, Rental rental) {
        rental.setStatus(request.getStatus());
        rental.setEndDateTime(request.getEndDateTime());
    }
}
