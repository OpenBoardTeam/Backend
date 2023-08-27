package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.CategoryDTO;
import com.oss.gitborad.data.dto.ProjectDTO;

public interface ProjectService {
    ProjectDTO.Info findOne(Long id);
    ProjectDTO.Info save(ProjectDTO.Request requestDTO);
    void update(ProjectDTO.Request requestDTO);
    ProjectDTO.ResponseBasicInfo getBasicInfo(String url);
    void delete(Long id);

}
