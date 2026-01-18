package org.example.RentACar.business.responses.Rental;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class GetAllActiveRentalResponse {
    private int id;
    private LocalDateTime startDateTime;
    private int carId;
    private int customerId;
}