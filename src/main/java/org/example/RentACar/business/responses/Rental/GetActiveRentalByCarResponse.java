package org.example.RentACar.business.responses.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.RentACar.business.responses.Customer.GetCustomerByActiveRentalResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetActiveRentalByCarResponse {
    private int id;
    private LocalDateTime startDateTime;
    private GetCustomerByActiveRentalResponse customer;
}