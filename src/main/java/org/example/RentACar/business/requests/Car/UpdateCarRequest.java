package org.example.RentACar.business.requests.Car;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

    @NotNull
    @Min(1)
    private Integer id;

    @NotBlank
    @Size(min = 7, max = 10)
    private String plate;

    @NotNull
    @Min(0)
    private Double dailyPrice;

    @NotNull
    private Integer modelYear;
}