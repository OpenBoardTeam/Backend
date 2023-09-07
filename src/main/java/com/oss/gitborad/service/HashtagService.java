package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.HashtagDTO;

import java.util.List;

public interface HashtagService {

    //Hashtag
    List<HashtagDTO.Info> findListByGroup(Long id);
    List<HashtagDTO.Info> findListByWriter(Long id);
    List<HashtagDTO.Info> findAll(int pageNumber, int size);
    void saveHashtag(HashtagDTO.Request requestDTO);
    void deleteHashtag(Long id, Long userId);

    //Hashtag group
    void saveHashtagGroup(HashtagDTO.GroupRequest requestDTO);
    void deleteHashtagGroup(Long id);

}
