package com.oss.gitborad.service.impl;

import com.oss.gitborad.data.domain.Category;
import com.oss.gitborad.data.domain.Interest;
import com.oss.gitborad.data.domain.User;
import com.oss.gitborad.data.dto.InterestDTO;
import com.oss.gitborad.repository.CategoryRepository;
import com.oss.gitborad.repository.InterestRepository;
import com.oss.gitborad.repository.UserRepository;
import com.oss.gitborad.service.InterestService;
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
    private final CategoryRepository categoryRepository;

    public InterestServiceImpl(InterestRepository interestRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.interestRepository = interestRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
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
    public void save(InterestDTO.Request requestDTO) {
        User user = userRepository.getById(requestDTO.getUserId());

        for(String i : requestDTO.getCategories()){
            //TODO: 카테고리가 존재하지 않을 경우 예외처리 필요
            Category category = categoryRepository.findByName(i);

            Interest interest = Interest.builder()
                    .user(user)
                    .category(category)
                    .build();

            interestRepository.save(interest);
        }
    }

    @Override
    public void delete(Long id) {
        interestRepository.deleteById(id);
    }
}
