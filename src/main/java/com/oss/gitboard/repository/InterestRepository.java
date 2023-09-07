package com.oss.gitboard.repository;

import com.oss.gitboard.data.domain.Interest;
import com.oss.gitboard.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Page<Interest> findByUser(User user, Pageable pageable);
    int countByUser(User user);
}
