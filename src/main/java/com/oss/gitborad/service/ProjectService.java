package com.oss.gitborad.service;

import com.oss.gitborad.data.domain.Category;
import com.oss.gitborad.data.domain.User;
import com.oss.gitborad.data.dto.CategoryDTO;
import com.oss.gitborad.data.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDTO.Info findOne(Long id);
    List<ProjectDTO.Info> findListByUser(Long id);
    ProjectDTO.Info save(ProjectDTO.Request requestDTO);
    void update(ProjectDTO.Request requestDTO);
    ProjectDTO.ResponseBasicInfo getBasicInfo(String url);
    void delete(Long id);

}
