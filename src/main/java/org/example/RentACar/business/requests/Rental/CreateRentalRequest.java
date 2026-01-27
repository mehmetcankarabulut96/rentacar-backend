package org.example.RentACar.business.requests.Rental;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRentalRequest {

    @NotNull
    @Min(1)
    private Integer carId;

    @NotNull
    @Min(1)
    private Integer customerId;
}