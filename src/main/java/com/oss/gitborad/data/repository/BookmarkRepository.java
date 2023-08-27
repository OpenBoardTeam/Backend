package com.oss.gitborad.data.repository;

import com.oss.gitborad.data.domain.Bookmark;
import com.oss.gitborad.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Page<Bookmark> findByUser(User user, Pageable pageable);
    int countByUser(User user);
}
