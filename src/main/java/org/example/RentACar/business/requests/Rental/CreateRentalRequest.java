package org.example.RentACar.business.requests.Rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRentalRequest {
    private int carId;
    private int customerId;
}