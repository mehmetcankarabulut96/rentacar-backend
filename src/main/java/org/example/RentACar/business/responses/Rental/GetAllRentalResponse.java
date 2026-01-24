package org.example.RentACar.business.responses.Rental;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.RentACar.entities.enums.RentalStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class GetAllRentalResponse {
    private int id;
    private RentalStatus status;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int carId;
    private int customerId;
}