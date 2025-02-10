package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsernameOrEmail(String username, String email);
}
