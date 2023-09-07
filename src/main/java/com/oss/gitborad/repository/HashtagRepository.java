package com.oss.gitborad.repository;

import com.oss.gitborad.data.domain.Hashtag;
import com.oss.gitborad.data.domain.HashtagGroup;
import com.oss.gitborad.data.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Page<Hashtag> findByGroup(HashtagGroup hashtagGroup, Pageable pageable);
    Page<Hashtag> findByWriter(User writer, Pageable pageable);
    Page<Hashtag> findByCertified(Boolean certified, Pageable pageable);
    Page<Hashtag> findAll(Pageable pageable);

    int countByGroup(HashtagGroup hashtagGroup);
    int countByWriter(User user);

    Hashtag findByName(String name);
}
