package org.example.RentACar.business.responses.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.RentACar.business.responses.Rental.GetActiveRentalsByCustomerResponse;
import org.example.RentACar.business.responses.Rental.GetRecentRentalsByCustomerResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int rentalCount;
    private List<GetActiveRentalsByCustomerResponse> activeRentals;
    private List<GetRecentRentalsByCustomerResponse> recentRentals;
}