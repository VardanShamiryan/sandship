package com.rockbitegames.sandship.validation;

import com.rockbitegames.sandship.exception.ValidationException;
import com.rockbitegames.sandship.exception.response.ValidationErrorResponse;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.util.CollectionUtils.isEmpty;

@RequiredArgsConstructor
public abstract class AbstractValidator<DTO> {

    public Set<ValidationErrorResponse.ValidationError> addValidation(DTO dto) {
        return Collections.emptySet();
    }

    public void addValidationError(Set<ValidationErrorResponse.ValidationError> validationErrors, String fieldName, String errorDescription) {
        validationErrors.add(ValidationErrorResponse.ValidationError.builder().fieldName(fieldName).errorDescription(errorDescription).build());
    }

    public void validate(DTO dto) {
        Set<ValidationErrorResponse.ValidationError> validationErrors = new HashSet<>(addValidation(dto));
        if (!isEmpty(validationErrors)) {
            throw new ValidationException(new ValidationErrorResponse(validationErrors));
        }
    }

}
