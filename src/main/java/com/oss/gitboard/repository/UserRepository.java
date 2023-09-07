package com.oss.gitboard.repository;

import com.oss.gitboard.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByGitUrl(String email);
    Optional<User> findByUsername(String username);
}
