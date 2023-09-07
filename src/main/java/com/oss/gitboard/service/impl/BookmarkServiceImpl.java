package com.oss.gitboard.service.impl;

import com.oss.gitboard.data.domain.Bookmark;
import com.oss.gitboard.data.domain.User;
import com.oss.gitboard.data.dto.BookmarkDTO;
import com.oss.gitboard.repository.BookmarkRepository;
import com.oss.gitboard.repository.ProjectRepository;
import com.oss.gitboard.repository.UserRepository;
import com.oss.gitboard.service.BookmarkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<BookmarkDTO.InfoForList> findListByUser(Long userId) {
        User user = userRepository.getById(userId);

        int bookmarkCountByUser = bookmarkRepository.countByUser(user);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, bookmarkCountByUser, sort);
        Page<BookmarkDTO.InfoForList> response = bookmarkRepository.findByUser(user, pageable).map(x -> new BookmarkDTO.InfoForList(x) );

        List<BookmarkDTO.InfoForList> pageRequestDTO = new ArrayList<>();
        for (BookmarkDTO.InfoForList i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public BookmarkDTO.Info save(BookmarkDTO.Request requestDTO) {
        Bookmark bookmark = Bookmark.builder()
                .user(userRepository.getById(requestDTO.getUserId()))
                .project(projectRepository.getById(requestDTO.getProjectId()))
                .build();

        bookmarkRepository.save(bookmark);

        BookmarkDTO.Info saveDTO = new BookmarkDTO.Info(bookmark);

        return saveDTO;
    }

    @Override
    public void delete(Long id, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        if(!user.getId().equals(userId))
            return;
        bookmarkRepository.deleteById(id);
    }
}
