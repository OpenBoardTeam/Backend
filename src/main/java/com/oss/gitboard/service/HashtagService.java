package com.oss.gitboard.service;

import com.oss.gitboard.data.dto.HashtagDTO;

import java.util.List;

public interface HashtagService {

    //Hashtag
    List<HashtagDTO.Info> findListByGroup(Long id);
    List<HashtagDTO.Info> findListByWriter(Long id);
    HashtagDTO.GroupListInfo findHashtagByCertified(long categoryGroupId);
    List<HashtagDTO.Info> findAll(int pageNumber, int size);
    void saveHashtag(HashtagDTO.Request requestDTO);
    void deleteHashtag(Long id, Long userId);

    //Hashtag group
    void saveHashtagGroup(HashtagDTO.GroupRequest requestDTO);
    void deleteHashtagGroup(Long id);

}