package com.oss.gitborad.repository;

import com.oss.gitborad.data.domain.Project;
import com.oss.gitborad.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByUser(User user, Pageable pageable);
    int countByUser(User user);
}
