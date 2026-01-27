package org.example.RentACar.business.requests.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateModelRequest {

    @NotNull
    @Min(1)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Min(1)
    private int brandId;
}