package org.example.RentACar.business.concretes;

import org.example.RentACar.business.abstracts.BrandService;
import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.rules.ModelBusinessRules;
import org.example.RentACar.dataAccess.abstracts.ModelRepository;
import org.example.RentACar.entities.concretes.Brand;
import org.example.RentACar.entities.concretes.Model;
import org.example.RentACar.utils.exception.BusinessException;
import org.example.RentACar.utils.exception.Model.ModelNotFoundException;
import org.example.RentACar.utils.mapper.Model.ModelMapperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModelManagerTest {

    @Mock
    private ModelBusinessRules modelBusinessRules;
    @Mock
    private BrandService brandService;
    @Mock
    private ModelRepository modelRepository;
    @Mock
    private ModelMapperService modelMapperService;

    @InjectMocks
    private ModelManager modelManager;

    @Test
    void add_whenBusinessRulesPass_shouldSaveModel(){

        // Arrange
        CreateModelRequest request = new CreateModelRequest("Corolla", 1);
        Model model = new Model();

        when(modelMapperService.toModel(request)).thenReturn(model);

        // Act
        modelManager.add(request);

        // Assert
        verify(modelBusinessRules).checkIfModelExistsByName("Corolla");
        verify(brandService).checkIfBrandExists(1);
        verify(modelBusinessRules).checkIfModelLimitExceededForBrand(1);
        verify(modelRepository, times(1)).save(model);
    }

    @Test
    void add_whenModelNameAlreadyExist_shouldThrowException(){

        // Arrange
        CreateModelRequest request = new CreateModelRequest("Corolla", 1);

        doThrow(new BusinessException("Model Corolla is already exists"))
                .when(modelBusinessRules).checkIfModelExistsByName("Corolla");

        // Act & Assert
        assertThrows(BusinessException.class, () -> modelManager.add(request));

        // Extra Assert
        verifyNoInteractions(brandService);
    }

    @Test
    void add_whenBrandDoesNotExist_shouldThrowException(){

        // Arrange
        CreateModelRequest request = new CreateModelRequest("Corolla", 1);

        doThrow(new BusinessException("Brand not found"))
                .when(brandService).checkIfBrandExists(1);

        // Act & Assert
        assertThrows(BusinessException.class, () -> modelManager.add(request));

        // Extra Assert
        verify(modelBusinessRules).checkIfModelExistsByName("Corolla");
        verifyNoMoreInteractions(modelBusinessRules);
    }

    @Test
    void add_whenBrandModelLimitExceed_shouldThrowException(){

        CreateModelRequest request = new CreateModelRequest("Corolla", 1);

        doThrow(new BusinessException("Brand model limit exceeded"))
                .when(modelBusinessRules).checkIfModelLimitExceededForBrand(1);

        assertThrows(BusinessException.class, () -> modelManager.add(request));

        verifyNoInteractions(modelMapperService);
    }

    @Test
    void update_whenBusinessRulesPass_shouldUpdateAndSaveModel(){

        UpdateModelRequest request = new UpdateModelRequest(1, "Benz_v2", 1);

        Brand currentBrand = new Brand();
        currentBrand.setId(2);

        Model currentModel = new Model();
        currentModel.setId(1);
        currentModel.setName("Benz");
        currentModel.setBrand(currentBrand);

        when(modelRepository.findById(1)).thenReturn(Optional.of(currentModel));

        modelManager.update(request);

        verify(modelBusinessRules).checkIfModelNameExistsForUpdate(1, "Benz_v2");
        verify(modelBusinessRules).checkIfBrandCanBeChanged(1, 2);
        verify(modelMapperService).updateModel(request, currentModel);
        verify(modelRepository).save(currentModel);
    }

    @Test
    void update_whenModelDoesNotExist_shouldThrowException(){

        UpdateModelRequest request = new UpdateModelRequest(1, "Benz_v2", 1);

        when(modelRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ModelNotFoundException.class, () -> modelManager.update(request));

        verifyNoInteractions(modelBusinessRules);
    }

    @Test
    void update_whenModelAlreadyExist_shouldThrowException(){

        UpdateModelRequest request = new UpdateModelRequest(1, "Benz", 1);

        Model currentModel = new Model();

        when(modelRepository.findById(1)).thenReturn(Optional.of(currentModel));

        doThrow(new BusinessException("Model Benz is already exists"))
                .when(modelBusinessRules).checkIfModelNameExistsForUpdate(1, "Benz");

        assertThrows(BusinessException.class, () -> modelManager.update(request));

        verify(modelRepository).findById(1);
        verify(modelBusinessRules).checkIfModelNameExistsForUpdate(1, "Benz");
        verifyNoMoreInteractions(modelBusinessRules);
    }

    @Test
    void update_whenBrandCantBeChanged_shouldThrowException(){

        UpdateModelRequest request = new UpdateModelRequest(1, "Benz", 1);

        Brand currentBrand = new Brand();
        currentBrand.setId(2);
        currentBrand.setName("Mercedes");

        Model currentModel = new Model();
        currentModel.setId(1);
        currentModel.setName("Benz");
        currentModel.setBrand(currentBrand);

        when(modelRepository.findById(1)).thenReturn(Optional.of(currentModel));

        doThrow(new BusinessException("brand can not be changed"))
                .when(modelBusinessRules)
                .checkIfBrandCanBeChanged(1, 2);

        assertThrows(BusinessException.class, () -> modelManager.update(request));

        verify(modelBusinessRules).checkIfModelNameExistsForUpdate(1, "Benz");
        verifyNoInteractions(modelMapperService);
        verifyNoInteractions(modelRepository);
    }
}