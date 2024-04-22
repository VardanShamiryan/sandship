package com.rockbitegames.sandship.validation;

import com.rockbitegames.sandship.dto.MaterialTypeDto;
import com.rockbitegames.sandship.exception.response.ValidationErrorResponse;
import com.rockbitegames.sandship.repository.MaterialTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MaterialTypeValidator extends AbstractValidator<MaterialTypeDto> {
    @Autowired
    MaterialTypeRepository materialTypeRepository;

    @Override
    public Set<ValidationErrorResponse.ValidationError> addValidation(MaterialTypeDto materialTypeDto) {
        Set<ValidationErrorResponse.ValidationError> validationErrors = new HashSet<>();
        if (materialTypeDto == null) {
            addValidationError(validationErrors, "MaterialTypeDto", "MaterialTypeDto can't be null");
        } else {
            if (materialTypeDto.getName() == null) {
                addValidationError(validationErrors, "Name", "Name can't be empty");
            } else if (materialTypeDto.getMaxCapacity() == null) {
                addValidationError(validationErrors, "MaxCapacity", "MaxCapacity can't be empty");
            } else if (materialTypeRepository.existsByName(materialTypeDto.getName())) {
                addValidationError(validationErrors, "Name", "Material Type already exists");
            }
        }
        return validationErrors;
    }
}
