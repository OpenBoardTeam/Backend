package com.oss.gitborad.service.impl;

import com.oss.gitborad.data.domain.Hashtag;
import com.oss.gitborad.data.domain.HashtagGroup;
import com.oss.gitborad.data.domain.User;
import com.oss.gitborad.data.dto.HashtagDTO;
import com.oss.gitborad.repository.HashtagGroupRepository;
import com.oss.gitborad.repository.HashtagRepository;
import com.oss.gitborad.repository.UserRepository;
import com.oss.gitborad.service.HashtagService;
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
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final HashtagGroupRepository hashtagGroupRepository;
    private final UserRepository userRepository;

    public HashtagServiceImpl(HashtagRepository hashtagRepository, HashtagGroupRepository hashtagGroupRepository, UserRepository userRepository) {
        this.hashtagRepository = hashtagRepository;
        this.hashtagGroupRepository = hashtagGroupRepository;
        this.userRepository = userRepository;
    }

    //Category
    @Override
    public List<HashtagDTO.Info> findListByGroup(Long id) {
        HashtagGroup hashtagGroup = hashtagGroupRepository.getById(id);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, hashtagRepository.countByGroup(hashtagGroup), sort);
        Page<HashtagDTO.Info> response = hashtagRepository.findByGroup(hashtagGroup, pageable).map(x -> new HashtagDTO.Info(x));

        List<HashtagDTO.Info> pageRequestDTO = new ArrayList<>();
        for (HashtagDTO.Info i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public List<HashtagDTO.Info> findListByWriter(Long id) {
        User user = userRepository.getById(id);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, hashtagRepository.countByWriter(user), sort);
        Page<HashtagDTO.Info> response = hashtagRepository.findByWriter(user, pageable).map(x -> new HashtagDTO.Info(x));

        List<HashtagDTO.Info> pageRequestDTO = new ArrayList<>();
        for (HashtagDTO.Info i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public List<HashtagDTO.Info> findAll(int pageNumber, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateAT");
        Pageable pageable = PageRequest.of(pageNumber, size , sort);
        Page<HashtagDTO.Info> response = hashtagRepository.findAll(pageable).map(x -> new HashtagDTO.Info(x));

        List<HashtagDTO.Info> pageRequestDTO = new ArrayList<>();
        for (HashtagDTO.Info i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public HashtagDTO.GroupListInfo findHashtagByCertified(long categoryGroupId) {
        HashtagGroup hashtagGroup = hashtagGroupRepository.getById(categoryGroupId);

        HashtagDTO.GroupListInfo groupListInfoDTO = new HashtagDTO.GroupListInfo(hashtagGroup);

        return groupListInfoDTO;
    }

    @Override
    public void saveHashtag(HashtagDTO.Request requestDTO) {
        User user = userRepository.getById(requestDTO.getWriterId());

        Hashtag hashtag = Hashtag.builder()
                .writer(user)
                .name(requestDTO.getName())
                .group(hashtagGroupRepository.getById(requestDTO.getGroupId()))
                .certified(requestDTO.isCertified())
                .build();

        hashtagRepository.save(hashtag);
    }

    @Override
    public void deleteHashtag(Long id, Long userId) {
        Hashtag hashtag = hashtagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));
        if(!hashtag.getWriter().getId().equals(userId))
            throw new RuntimeException("삭제 권한이 없습니다. user: "+userId);

        hashtagRepository.deleteById(id);
    }

    //Category Group
    @Override
    public void saveHashtagGroup(HashtagDTO.GroupRequest requestDTO) {
        HashtagGroup hashtagGroup = HashtagGroup.builder()
                .name(requestDTO.getName())
                .build();

        hashtagGroupRepository.save(hashtagGroup);
    }

    @Override
    public void deleteHashtagGroup(Long id) {
        hashtagGroupRepository.deleteById(id);
    }
}
