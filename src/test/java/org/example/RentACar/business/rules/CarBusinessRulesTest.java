package org.example.RentACar.business.rules;

import org.example.RentACar.business.abstracts.ModelService;
import org.example.RentACar.dataAccess.abstracts.CarRepository;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.enums.CarState;
import org.example.RentACar.utils.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarBusinessRulesTest {

    @Mock
    CarRepository carRepository;

    @Mock
    RentalRepository rentalRepository;

    @Mock
    ModelService modelService;

    @InjectMocks
    CarBusinessRules carBusinessRules;

    @Test
    void checkIfCarStateOkForRented_whenCarIsAvailable_shouldPass(){

        when(carRepository.existsByIdAndState(1, CarState.AVAILABLE))
                .thenReturn(true);

        assertDoesNotThrow(() -> carBusinessRules.checkIfCarStateOkForRented(1));

        verify(carRepository).existsByIdAndState(1, CarState.AVAILABLE);
    }

    @Test
    void checkIfCarStateOkForRented_whenCarIsNotAvailable_shouldThrowException(){

        when(carRepository.existsByIdAndState(1, CarState.AVAILABLE))
                .thenReturn(false);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarStateOkForRented(1));

        verify(carRepository).existsByIdAndState(1, CarState.AVAILABLE);
    }
}