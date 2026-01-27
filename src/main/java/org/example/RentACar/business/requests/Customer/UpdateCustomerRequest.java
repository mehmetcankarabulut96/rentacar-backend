package org.example.RentACar.business.requests.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCustomerRequest {

    @NotNull
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 25)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 30)
    private String lastName;

    @NotBlank
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{3}$")
    private String email;
}