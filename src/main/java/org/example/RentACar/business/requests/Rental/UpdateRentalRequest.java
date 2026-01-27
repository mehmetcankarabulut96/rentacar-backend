package org.example.RentACar.business.requests.Rental;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.RentACar.entities.enums.RentalStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateRentalRequest {

    @NotNull
    @Min(1)
    private Integer id;

    @NotNull
    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}