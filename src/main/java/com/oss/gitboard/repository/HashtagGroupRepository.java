package com.oss.gitboard.repository;

import com.oss.gitboard.data.domain.HashtagGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagGroupRepository extends JpaRepository<HashtagGroup, Long> {
}
