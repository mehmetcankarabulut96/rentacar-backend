package org.example.RentACar.business.responses.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetActiveRentalsByCustomerResponse {
    private int id;
    private LocalDateTime startDateTime;
    private String carPlate;
}