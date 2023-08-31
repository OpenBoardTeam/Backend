package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    //category
    List<CategoryDTO.Info> findListByGroup(Long id);
    List<CategoryDTO.Info> findListByWriter(Long id);

    List<CategoryDTO.Info> findAll(int pageNumber, int size);
    void saveCategory(CategoryDTO.Request requestDTO);
    void deleteCategory(Long id, Long userId);

    //category group
    void saveCategoryGroup(CategoryDTO.GroupRequest requestDTO);
    void deleteCategoryGroup(Long id);

}
