package com.mobeasy.api.services.security;

import com.mobeasy.api.entities.dto.LoginDto;
import com.mobeasy.api.exceptions.UnauthorizedException;
import com.mobeasy.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mobeasy.api.entities.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return jwtService.generateToken(user);
        }
        throw new UnauthorizedException("Invalid login credentials");
    }

    public User authenticate(Authentication authentication) {
        return userRepository.findById(jwtService.getUserIdFromToken(authentication.getPrincipal().toString())).orElseThrow(() -> new UnauthorizedException("Invalid token"));
    }
}