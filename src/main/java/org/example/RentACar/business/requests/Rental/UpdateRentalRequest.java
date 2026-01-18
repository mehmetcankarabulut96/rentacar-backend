package org.example.RentACar.business.requests.Rental;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.RentACar.entities.enums.RentalStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateRentalRequest {
    private int id;
    private RentalStatus status;
    private LocalDateTime endDateTime;
    private boolean deleted;
}