package org.example.RentACar.business.responses.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerByActiveRentalResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}