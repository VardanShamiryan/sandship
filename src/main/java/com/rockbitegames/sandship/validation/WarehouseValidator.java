package com.rockbitegames.sandship.validation;

import com.rockbitegames.sandship.dto.WarehouseDto;
import com.rockbitegames.sandship.exception.response.ValidationErrorResponse.ValidationError;
import com.rockbitegames.sandship.repository.UserRepository;
import com.rockbitegames.sandship.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class WarehouseValidator extends AbstractValidator<WarehouseDto> {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Set<ValidationError> addValidation(WarehouseDto warehouseDto) {
        Set<ValidationError> validationErrors = new HashSet<>();
        if (warehouseDto == null) {
            addValidationError(validationErrors, "WarehouseDto", "WarehouseDto can't be null");
        } else {
            if (warehouseDto.getName() == null) {
                addValidationError(validationErrors, "Warehouse Name", "Warehouse Name can't be empty");
            } else if (warehouseRepository.existsByName(warehouseDto.getName())) {
                addValidationError(validationErrors, "Warehouse Name", "Warehouse Name exists");
            } else if (!userRepository.existsById(warehouseDto.getOwner().getId())) {
                addValidationError(validationErrors, "Owner", "Owner doesn't exists");
            }
        }
        return validationErrors;
    }

}
