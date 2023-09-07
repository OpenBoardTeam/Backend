package com.oss.gitboard.repository;

import com.oss.gitboard.data.domain.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
}
