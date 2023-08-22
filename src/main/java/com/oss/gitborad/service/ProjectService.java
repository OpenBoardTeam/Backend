package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.CategoryDTO;
import com.oss.gitborad.data.dto.ProjectDTO;

public interface ProjectService {
    ProjectDTO.info findOne(Long id);
    ProjectDTO.info save(ProjectDTO.request requestDTO);
    void update(ProjectDTO.request requestDTO);
    void delete(Long id);

}
