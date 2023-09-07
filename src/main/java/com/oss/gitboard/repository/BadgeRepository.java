package com.oss.gitboard.repository;

import com.oss.gitboard.data.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
