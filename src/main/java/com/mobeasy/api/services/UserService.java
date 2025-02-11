package com.mobeasy.api.services;

import com.mobeasy.api.entities.User;
import com.mobeasy.api.entities.dto.UserDto;
import com.mobeasy.api.exceptions.ConflictException;
import com.mobeasy.api.exceptions.ForbiddenException;
import com.mobeasy.api.repositories.UserRepository;
import com.mobeasy.api.services.mappers.UserMapperService;
import com.mobeasy.api.services.security.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapperService userMapperService;

    public UserDto register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ConflictException("Email already exists");
        } else {
            return userMapperService.toDTO(userRepository.save(userMapperService.toEntity(userDto)));
        }
    }

    public UserDto getMe(Authentication authentication) {
        return userMapperService.toDTO(authService.authenticate(authentication));
    }

    public UserDto updateMe(Authentication authentication, UserDto userDto) {
        User user = authService.authenticate(authentication);
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAdmin(userDto.isAdmin());

        return userMapperService.toDTO(userRepository.save(user));
    }

    public UserDto updatePassword(String oldPassword, String newPassword, Authentication authentication) {
        User user = authService.authenticate(authentication);

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return userMapperService.toDTO(userRepository.save(user));
        } else {
            throw new ForbiddenException("Invalid password");
        }
    }

    public void deleteMe(Authentication authentication) {
        User user = authService.authenticate(authentication);
        userRepository.delete(user);
    }
}

