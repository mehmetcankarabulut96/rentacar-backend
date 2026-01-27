package org.example.RentACar.business.requests.Brand;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateBrandRequest {
    @NotNull
    @Min(1)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
}