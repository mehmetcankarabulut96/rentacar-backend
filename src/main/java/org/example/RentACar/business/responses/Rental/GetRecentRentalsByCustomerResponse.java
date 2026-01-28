package org.example.RentACar.business.responses.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRecentRentalsByCustomerResponse {
    private int rentalId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String carPlate;
}