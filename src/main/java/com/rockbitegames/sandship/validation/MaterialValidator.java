package com.rockbitegames.sandship.validation;

import com.rockbitegames.sandship.dto.MaterialDto;
import com.rockbitegames.sandship.exception.response.ValidationErrorResponse;
import com.rockbitegames.sandship.repository.MaterialRepository;
import com.rockbitegames.sandship.repository.MaterialTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MaterialValidator extends AbstractValidator<MaterialDto> {
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    MaterialTypeRepository materialTypeRepository;

    @Override
    public Set<ValidationErrorResponse.ValidationError> addValidation(MaterialDto materialDto) {
        Set<ValidationErrorResponse.ValidationError> validationErrors = new HashSet<>();
        if (materialDto == null) {
            addValidationError(validationErrors, "MaterialDto", "MaterialDto can't be null");
        } else {
            if (materialDto.getName() == null) {
                addValidationError(validationErrors, "Material Name", "Material Name can't be empty");
            } else if (materialRepository.existsByName(materialDto.getName())) {
                addValidationError(validationErrors, "Material Name", "Material Name exists");
            } else if (materialDto.getMaterialTypeDto() == null) {
                addValidationError(validationErrors, "Material Type", "Material Type can't be null");
            } else if (!materialTypeRepository.existsById(materialDto.getMaterialTypeDto().getId())) {
                addValidationError(validationErrors, "Material Type", "Material Type doesn't exists");
            }
        }
        return validationErrors;
    }


}
