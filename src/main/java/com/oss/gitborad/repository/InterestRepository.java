package com.oss.gitborad.repository;

import com.oss.gitborad.data.domain.Interest;
import com.oss.gitborad.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Page<Interest> findByUser(User user, Pageable pageable);
    int countByUser(User user);
}
