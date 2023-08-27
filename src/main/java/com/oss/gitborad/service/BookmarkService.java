package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.BookmarkDTO;

import java.util.List;

public interface BookmarkService {
    List<BookmarkDTO.InfoForList>  findListByUser(Long userId);
    BookmarkDTO.Info save(BookmarkDTO.Request requestDTO);
    void delete(Long id);
}
