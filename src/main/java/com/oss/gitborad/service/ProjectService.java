package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.ProjectDTO;
import com.oss.gitborad.data.dto.UserDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO.Info findOne(Long id);
    List<ProjectDTO.Info> findListByUser(Long id);
    ProjectDTO.Info save(ProjectDTO.Request requestDTO);
    void update(ProjectDTO.Request requestDTO);
    ProjectDTO.ResponseBasicInfo getBasicInfo(String url);
    void delete(Long id, UserDTO.Info principal);

}
