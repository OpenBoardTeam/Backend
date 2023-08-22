package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    //category
    List<CategoryDTO.info> findListByGroup(Long id);
    void saveCategory(CategoryDTO.request requestDTO);
    void deleteCategory(Long id);

    //category group
    void saveCategoryGroup(CategoryDTO.groupRequest requestDTO);
    void deleteCategoryGroup(Long id);

}
