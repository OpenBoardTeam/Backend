package com.oss.gitboard.service.impl;

import com.oss.gitboard.data.domain.Hashtag;
import com.oss.gitboard.data.domain.Interest;
import com.oss.gitboard.data.domain.User;
import com.oss.gitboard.data.dto.InterestDTO;
import com.oss.gitboard.data.dto.UserDTO;
import com.oss.gitboard.repository.HashtagRepository;
import com.oss.gitboard.repository.InterestRepository;
import com.oss.gitboard.repository.UserRepository;
import com.oss.gitboard.service.InterestService;
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
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    public InterestServiceImpl(InterestRepository interestRepository, UserRepository userRepository, HashtagRepository hashtagRepository) {
        this.interestRepository = interestRepository;
        this.userRepository = userRepository;
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public List<InterestDTO.Info> findListByUser(Long userId) {
        User user = userRepository.getById(userId);

        int interestCountByUser = interestRepository.countByUser(user);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, interestCountByUser, sort);
        Page<InterestDTO.Info> response = interestRepository.findByUser(user, pageable).map(x -> new InterestDTO.Info(x));

        List<InterestDTO.Info> pageRequestDTO = new ArrayList<>();
        for (InterestDTO.Info i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public void save(InterestDTO.Request requestDTO, UserDTO.Info principal) {
        User user = userRepository.getById(principal.getUser().getId());

        for(String i : requestDTO.getHashtagList()){
            //TODO: 카테고리가 존재하지 않을 경우 예외처리 필요
            Hashtag hashtag = hashtagRepository.findByName(i);

            Interest interest = Interest.builder()
                    .user(user)
                    .hashtag(hashtag)
                    .build();

            interestRepository.save(interest);
        }
    }

    @Override
    public void delete(Long id) {
        interestRepository.deleteById(id);
    }
}
