package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.BookmarkDTO;

import java.util.List;

public interface BookmarkService {
    List<BookmarkDTO.infoForList>  findListByUser(Long userId);
    BookmarkDTO.info save(BookmarkDTO.request requestDTO);
    void delete(Long id);
}
