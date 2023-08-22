package com.oss.gitborad.data.repository;

import com.oss.gitborad.data.domain.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Long> {

}
