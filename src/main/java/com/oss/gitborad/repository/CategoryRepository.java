package com.oss.gitborad.repository;

import com.oss.gitborad.data.domain.Category;
import com.oss.gitborad.data.domain.CategoryGroup;
import com.oss.gitborad.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByGroup(CategoryGroup categoryGroup, Pageable pageable);
    Page<Category> findByWriter(User writer, Pageable pageable);

    Page<Category> findAll(Pageable pageable);

    int countByGroup(CategoryGroup categoryGroup);
    int countByWriter(User user);

    Category findByName(String name);
}
