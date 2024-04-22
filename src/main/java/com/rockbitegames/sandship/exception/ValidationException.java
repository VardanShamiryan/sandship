package com.rockbitegames.sandship.exception;


import com.rockbitegames.sandship.exception.response.ValidationErrorResponse;
import lombok.Getter;

import java.util.Set;

@Getter
public class ValidationException extends RuntimeException {

    private final ValidationErrorResponse errorResponse;


    public ValidationException(ValidationErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    @Override
    public String getMessage() {
        return errorResponse.toString();
    }

    public Set<ValidationErrorResponse.ValidationError> getValidationErrors() {
        return errorResponse.getValidationErrors();
    }
}
