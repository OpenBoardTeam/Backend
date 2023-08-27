package com.oss.gitborad.data.repository;

import com.oss.gitborad.data.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
