package com.rockbitegames.sandship.exception.handler;


import com.rockbitegames.sandship.exception.ValidationException;
import com.rockbitegames.sandship.exception.response.ErrorResponse;
import com.rockbitegames.sandship.exception.response.ValidationErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest webRequest) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND, webRequest.getServletPath(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ValidationErrorResponse> handleValidationError(ValidationException e, HttpServletRequest webRequest) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationErrorResponse(HttpStatus.BAD_REQUEST, webRequest.getServletPath(), e.getValidationErrors()));
    }
}

