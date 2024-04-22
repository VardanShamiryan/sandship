package com.rockbitegames.sandship.validation;

import com.rockbitegames.sandship.dto.UserDto;
import com.rockbitegames.sandship.exception.response.ValidationErrorResponse.ValidationError;
import com.rockbitegames.sandship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserValidator extends AbstractValidator<UserDto> {
    @Autowired
    UserRepository userRepository;

    @Override
    public Set<ValidationError> addValidation(UserDto userDto) {
        Set<ValidationError> validationErrors = new HashSet<>();
        if (userDto == null) {
            addValidationError(validationErrors, "UserDto", "UserDto can't be null");
        } else {
            if (userDto.getEmail() == null) {
                addValidationError(validationErrors, "Email", "Email can't be empty");
            } else if (userRepository.existsByEmail(userDto.getEmail())) {
                addValidationError(validationErrors, "Email", "Email already exists");
            }
        }
        return validationErrors;
    }

}
