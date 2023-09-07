package com.oss.gitboard.service;

import com.oss.gitboard.data.dto.BookmarkDTO;

import java.util.List;

public interface BookmarkService {
    List<BookmarkDTO.InfoForList>  findListByUser(Long userId);
    BookmarkDTO.Info save(BookmarkDTO.Request requestDTO);
    void delete(Long id, Long userId);
}