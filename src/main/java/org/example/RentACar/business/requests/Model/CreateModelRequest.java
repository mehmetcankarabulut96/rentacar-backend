package org.example.RentACar.business.requests.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Min(1)
    private Integer brandId;
}