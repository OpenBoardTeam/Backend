package com.oss.gitborad.data.repository;

import com.oss.gitborad.data.domain.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
}
