package org.example.RentACar.business.responses.Rental;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.RentACar.entities.enums.RentalStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GetByIdRentalResponse {
    private int id;
    private RentalStatus status;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int carId;
    private int customerId;
}