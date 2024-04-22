package com.rockbitegames.sandship.exception.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.Set;

import static java.util.stream.Collectors.joining;

@Data
@Builder(builderMethodName = "validationErrorResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationErrorResponse extends ErrorResponse {

    Set<ValidationError> validationErrors;

    public ValidationErrorResponse(HttpStatus status, String path, Set<ValidationError> validationErrors) {
        super(status, path, "VALIDATION_ERROR");
        this.validationErrors = validationErrors;
    }

    @Override
    public String toString() {
        return super.toString() + ". Validation errors: \n" + validationErrors.stream()
                .map(it -> "Fields: " + it.getFieldName()
                        + ". Error description: " + it.getErrorDescription()
                        + ".")
                .collect(joining("\n"));
    }

    @Data
    @Builder
    public static class ValidationError {
        private final String fieldName;
        private final String errorDescription;
    }
}
