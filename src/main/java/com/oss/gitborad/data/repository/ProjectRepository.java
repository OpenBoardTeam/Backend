package com.oss.gitborad.data.repository;

import com.oss.gitborad.data.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
