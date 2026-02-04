package org.example.RentACar.business.requests.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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