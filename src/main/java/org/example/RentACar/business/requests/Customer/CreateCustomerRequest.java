package org.example.RentACar.business.requests.Customer;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
}