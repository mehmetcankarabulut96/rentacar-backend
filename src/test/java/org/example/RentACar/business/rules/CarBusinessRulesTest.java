package org.example.RentACar.business.rules;

import org.example.RentACar.business.abstracts.ModelService;
import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.dataAccess.abstracts.CarRepository;
import org.example.RentACar.dataAccess.abstracts.RentalRepository;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.enums.CarState;
import org.example.RentACar.entities.enums.RentalStatus;
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

    @Test
    void checkIfCarExists_whenCarIsExistsWithId_shouldPass(){

        when(carRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> carBusinessRules.checkIfCarExists(1));

        verify(carRepository).existsById(1);
    }

    @Test
    void checkIfCarExists_whenCarDoesNotExistsWithId_shouldThrowException(){

        when(carRepository.existsById(1)).thenReturn(false);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarExists(1));

        verify(carRepository).existsById(1);
    }

    @Test
    void checkIfCarExists_whenCarDoesNotExistsWithPlate_shouldPass(){
        String plate = "41TR41";

        when(carRepository.existsByPlate(plate)).thenReturn(false);

        carBusinessRules.checkIfCarExists(plate);
    }

    @Test
    void checkIfCarExists_whenCarExistsWithPlate_shouldThrowException(){
        String plate = "41TR41";

        when(carRepository.existsByPlate(plate)).thenReturn(true);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarExists(plate));

        verify(carRepository).existsByPlate(plate);
    }

    @Test
    void checkIfPlateUsedByAnotherCar_whenPlateNotExists_shouldPass(){
        int carId = 1;
        String plate = "41TR41";

        when(carRepository.existsByPlateAndIdNot(plate, carId)).thenReturn(false);

        carBusinessRules.checkIfPlateUsedByAnotherCar(plate, carId);
    }

    @Test
    void checkIfPlateUsedByAnotherCar_whenPlateExists_shouldThrowException(){
        int carId = 1;
        String plate = "41TR41";

        when(carRepository.existsByPlateAndIdNot(plate, carId)).thenReturn(true);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfPlateUsedByAnotherCar(plate, carId));

        verify(carRepository).existsByPlateAndIdNot(plate, carId);
    }

    @Test
    void checkIfCarCanBeDeleted_whenCarHasNoActiveRental_shouldPass(){

        when(rentalRepository.existsByCarIdAndStatus(1, RentalStatus.ACTIVE)).thenReturn(false);

        carBusinessRules.checkIfCarCanBeDeleted(1);
    }

    @Test
    void checkIfCarCanBeDeleted_whenCarHasActiveRental_shouldThrowException(){

        when(rentalRepository.existsByCarIdAndStatus(1, RentalStatus.ACTIVE)).thenReturn(true);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeDeleted(1));

        verify(rentalRepository).existsByCarIdAndStatus(1, RentalStatus.ACTIVE);
    }

    @Test
    void checkIfCarCanBeCreated_whenRulesPass_shouldSuccess(){
        CreateCarRequest request = new CreateCarRequest("41TR41", 15.30, 2025, 1);

        when(carRepository.existsByPlate("41TR41")).thenReturn(false);

        carBusinessRules.checkIfCarCanBeCreated(request);
    }

    @Test
    void checkIfCarCanBeCreated_whenCarAlreadyExistsWithPlate_shouldThrowException(){
        CreateCarRequest request = new CreateCarRequest("41TR41", 15.30, 2025, 1);

        when(carRepository.existsByPlate("41TR41")).thenReturn(true);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeCreated(request));

        verify(carRepository).existsByPlate("41TR41");
        verify(modelService, never()).checkIfModelExists(1);
    }

    @Test
    void checkIfCarCanBeCreated_whenModelDoesNotExists_shouldThrowException(){
        CreateCarRequest request = new CreateCarRequest("41TR41", 15.30, 2025, 1);

        doThrow(new BusinessException("model does not exists"))
                .when(modelService).checkIfModelExists(1);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeCreated(request));

        verify(carRepository).existsByPlate("41TR41");
        verify(modelService).checkIfModelExists(1);
    }

    @Test
    void checkIfCarCanBeCreated_whenDailyPriceIsNegative_shouldThrowException(){
        CreateCarRequest request = new CreateCarRequest("41TR41", -10.50, 2025, 1);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeCreated(request));

        verify(modelService).checkIfModelExists(1);
    }

    @Test
    void checkIfCarCanBeUpdated_whenRulesPass_shouldSuccess(){
        UpdateCarRequest request = new UpdateCarRequest(1, "41TR42", 18.99, 2022);
        Car currentCar = new Car();
        currentCar.setId(1);
        currentCar.setPlate("41TR41");
        currentCar.setDailyPrice(15.99);
        currentCar.setModelYear(2018);

        when(carRepository.existsByPlateAndIdNot("41TR42", 1)).thenReturn(false);

        carBusinessRules.checkIfCarCanBeUpdated(request, currentCar);
    }

    @Test
    void checkIfCarCanBeUpdated_whenPlateIsUsedByAnotherCar_shouldThrowException(){
        UpdateCarRequest request = new UpdateCarRequest(1, "41TR42", 18.99, 2022);
        Car currentCar = new Car();
        currentCar.setId(1);
        currentCar.setPlate("41TR41");
        currentCar.setDailyPrice(15.99);
        currentCar.setModelYear(2018);

        when(carRepository.existsByPlateAndIdNot("41TR42", 1)).thenReturn(true);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeUpdated(request, currentCar));

        verify(carRepository).existsByPlateAndIdNot("41TR42", 1);
    }

    @Test
    void checkIfCarCanBeUpdated_whenDailyPriceIsEqualsOrLowerThanZero_shouldThrowException(){
        UpdateCarRequest request = new UpdateCarRequest(1, "41TR42", -10.99, 2018);
        Car currentCar = new Car();
        currentCar.setId(1);
        currentCar.setPlate("41TR41");
        currentCar.setDailyPrice(15.99);
        currentCar.setModelYear(2018);

        when(carRepository.existsByPlateAndIdNot("41TR42", 1)).thenReturn(false);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeUpdated(request, currentCar));

        verify(carRepository).existsByPlateAndIdNot("41TR42", 1);
    }

    @Test
    void checkIfCarCanBeUpdated_whenDailyPriceIncreaseRateIsGreaterThan30Percent_shouldThrowException(){
        UpdateCarRequest request = new UpdateCarRequest(1, "41TR41", 100.99, 2018);
        Car currentCar = new Car();
        currentCar.setId(1);
        currentCar.setPlate("41TR41");
        currentCar.setDailyPrice(15.99);
        currentCar.setModelYear(2018);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeUpdated(request, currentCar));
    }

    @Test
    void checkIfCarCanBeUpdated_whenNewModelYearGreaterThanCurrentYear_shouldThrowException(){
        UpdateCarRequest request = new UpdateCarRequest(1, "41TR41", 15.99, 2036);
        Car currentCar = new Car();
        currentCar.setId(1);
        currentCar.setPlate("41TR41");
        currentCar.setDailyPrice(15.99);
        currentCar.setModelYear(2018);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeUpdated(request, currentCar));
    }

    @Test
    void checkIfCarCanBeUpdated_whenCarIsOlderThan10Years_shouldThrowException(){
        UpdateCarRequest request = new UpdateCarRequest(1, "41TR41", 15.99, 2015);
        Car currentCar = new Car();
        currentCar.setId(1);
        currentCar.setPlate("41TR41");
        currentCar.setDailyPrice(15.99);
        currentCar.setModelYear(2018);

        assertThrows(BusinessException.class, () -> carBusinessRules.checkIfCarCanBeUpdated(request, currentCar));
    }
}