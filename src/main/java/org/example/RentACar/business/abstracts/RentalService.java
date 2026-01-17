package org.example.RentACar.business.abstracts;

import org.example.RentACar.business.requests.Rental.CreateRentalRequest;

public interface RentalService {
    void add(CreateRentalRequest request);

}