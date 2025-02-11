package com.mobeasy.api.repositories;

import com.mobeasy.api.entities.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(@Email String email);

    boolean existsByEmail(@Email String email);
}
