package com.mobeasy.api.services.mappers;

import com.mobeasy.api.entities.User;
import com.mobeasy.api.entities.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserMapperService {
    private final PasswordEncoder passwordEncoder;

    public UserDto toDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAdmin(user.isAdmin());

        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAdmin(userDto.isAdmin());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return user;
    }
}
