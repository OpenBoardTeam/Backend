package com.oss.gitboard.repository;

import com.oss.gitboard.data.domain.Bookmark;
import com.oss.gitboard.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Page<Bookmark> findByUser(User user, Pageable pageable);
    int countByUser(User user);
}
