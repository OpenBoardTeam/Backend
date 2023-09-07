package com.oss.gitboard.repository;

import com.oss.gitboard.data.domain.Project;
import com.oss.gitboard.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByUser(User user, Pageable pageable);
    int countByUser(User user);
    Page<Project> findByNameContainingOrDescriptionContaining(String nameKeyword, String descKeyword, Pageable pageable);
    Page<Project> findAll(Pageable pageable);

}
