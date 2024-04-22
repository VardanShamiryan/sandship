package com.rockbitegames.sandship.service;

import com.rockbitegames.sandship.dto.UserDto;
import com.rockbitegames.sandship.mapper.UserMapper;
import com.rockbitegames.sandship.repository.UserRepository;
import com.rockbitegames.sandship.validation.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserValidator userValidator;
    private UserMapper mapper;

    public UserDto createNew(UserDto userDto) {
        userValidator.validate(userDto);
        return mapper.toDto(userRepository.save(mapper.toEntity(userDto)));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.toDto(user))
                .collect(Collectors.toList());
    }
}
