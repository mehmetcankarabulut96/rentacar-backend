package org.example.RentACar.business.requests.Customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCustomerRequest {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}