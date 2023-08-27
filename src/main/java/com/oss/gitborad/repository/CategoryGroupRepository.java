package com.oss.gitborad.repository;

import com.oss.gitborad.data.domain.CategoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryGroupRepository extends JpaRepository<CategoryGroup, Long> {
}
