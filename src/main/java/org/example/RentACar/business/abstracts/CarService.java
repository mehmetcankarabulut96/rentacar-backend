package org.example.RentACar.business.abstracts;

import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    Page<GetAllCarsResponse> getAll(Pageable pageable);
}