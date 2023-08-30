package com.oss.gitborad.service.impl;

import com.oss.gitborad.data.domain.Category;
import com.oss.gitborad.data.domain.CategoryGroup;
import com.oss.gitborad.data.domain.User;
import com.oss.gitborad.data.dto.CategoryDTO;
import com.oss.gitborad.repository.CategoryGroupRepository;
import com.oss.gitborad.repository.CategoryRepository;
import com.oss.gitborad.repository.UserRepository;
import com.oss.gitborad.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryGroupRepository categoryGroupRepository;
    private final UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryGroupRepository categoryGroupRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryGroupRepository = categoryGroupRepository;
        this.userRepository = userRepository;
    }

    //Category
    @Override
    public List<CategoryDTO.Info> findListByGroup(Long id) {
        CategoryGroup categoryGroup = categoryGroupRepository.getById(id);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, categoryRepository.countByGroup(categoryGroup), sort);
        Page<CategoryDTO.Info> response = categoryRepository.findByGroup(categoryGroup, pageable).map(x -> new CategoryDTO.Info(x));

        List<CategoryDTO.Info> pageRequestDTO = new ArrayList<>();
        for (CategoryDTO.Info i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public List<CategoryDTO.Info> findListByWriter(Long id) {
        User user = userRepository.getById(id);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, categoryRepository.countByWriter(user), sort);
        Page<CategoryDTO.Info> response = categoryRepository.findByWriter(user, pageable).map(x -> new CategoryDTO.Info(x));

        List<CategoryDTO.Info> pageRequestDTO = new ArrayList<>();
        for (CategoryDTO.Info i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public List<CategoryDTO.Info> findAll(int pageNumber, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateAT");
        Pageable pageable = PageRequest.of(pageNumber, size , sort);
        Page<CategoryDTO.Info> response = categoryRepository.findAll(pageable).map(x -> new CategoryDTO.Info(x));

        List<CategoryDTO.Info> pageRequestDTO = new ArrayList<>();
        for (CategoryDTO.Info i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public void saveCategory(CategoryDTO.Request requestDTO) {
        Category category = Category.builder()
                .name(requestDTO.getName())
                .group(categoryGroupRepository.getById(requestDTO.getGroupId()))
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));
//        if(category == null)
//            throw new RuntimeException("삭제 권한이 없습니다. user: \"+userId");
        // TODO: 카테고리를 만든 유저가 userId와 같은지 비교하는 로직

        categoryRepository.deleteById(id);
    }

    //Category Group
    @Override
    public void saveCategoryGroup(CategoryDTO.GroupRequest requestDTO) {
        CategoryGroup categoryGroup = CategoryGroup.builder()
                .name(requestDTO.getName())
                .build();

        categoryGroupRepository.save(categoryGroup);
    }

    @Override
    public void deleteCategoryGroup(Long id, Long userId) {
        categoryGroupRepository.deleteById(id);
    }
}
