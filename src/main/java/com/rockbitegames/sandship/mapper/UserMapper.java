package com.rockbitegames.sandship.mapper;

import com.rockbitegames.sandship.domain.User;
import com.rockbitegames.sandship.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto userDto) {
        User.UserBuilder builder = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail());
        return builder.build();
    }

    public UserDto toDto(User user) {
        UserDto.UserDtoBuilder builder = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail());
        return builder.build();

    }

}
