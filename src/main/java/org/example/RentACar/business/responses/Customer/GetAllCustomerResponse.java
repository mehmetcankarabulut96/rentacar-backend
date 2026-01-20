package org.example.RentACar.business.responses.Customer;

import lombok.Data;

@Data
public class GetAllCustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}