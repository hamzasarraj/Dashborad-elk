package com.vermeg.dashboard.repositories;

import com.vermeg.dashboard.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findOneByEmail(String username);

    boolean existsByEmail(String email);

    boolean existsByEmailAndId(String email, Long id);
}
