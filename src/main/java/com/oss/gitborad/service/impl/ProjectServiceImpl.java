package com.oss.gitborad.service.impl;

import com.oss.gitborad.data.domain.Category;
import com.oss.gitborad.data.domain.Project;
import com.oss.gitborad.data.domain.ProjectCategory;
import com.oss.gitborad.data.domain.User;
import com.oss.gitborad.data.dto.ProjectDTO;
import com.oss.gitborad.data.repository.CategoryRepository;
import com.oss.gitborad.data.repository.ProjectCategoryRepository;
import com.oss.gitborad.data.repository.ProjectRepository;
import com.oss.gitborad.repository.UserRepository;
import com.oss.gitborad.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProjectCategoryRepository projectCategoryRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, CategoryRepository categoryRepository, ProjectCategoryRepository projectCategoryRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.projectCategoryRepository = projectCategoryRepository;
    }

    @Override
    public ProjectDTO.Info findOne(Long id) {
        ProjectDTO.Info findOneDto = new ProjectDTO.Info(projectRepository.getById(id));
        return findOneDto;
    }

    @Override
    public List<ProjectDTO.Info> findListByUser(Long id) {
        User user = userRepository.getById(id);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, projectRepository.countByUser(user), sort);
        Page<ProjectDTO.Info> response = projectRepository.findByUser(user, pageable).map(ProjectDTO.Info::new);

        List<ProjectDTO.Info> pageRequestDTO = new ArrayList<>();
        for (ProjectDTO.Info i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public ProjectDTO.Info save(ProjectDTO.Request requestDTO) {
        User user = userRepository.getById(requestDTO.getUserId());

        Project project = Project.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .gitUrl(requestDTO.getGitUrl())
                .user(user)
                .build();

        projectRepository.save(project);

        for(String i : requestDTO.getCategories()){
            Category category = categoryRepository.findByName(i);

            ProjectCategory projectCategory = ProjectCategory.builder()
                    .project(project)
                    .category(category)
                    .build();

            projectCategoryRepository.save(projectCategory);

        }

        //TODO: 저장 후 반환하는 Info 정보에 카테고리 데이터가 반영이 안되어있는 버그 수정 필요
        ProjectDTO.Info findOneDto = new ProjectDTO.Info(project);

        return findOneDto;
    }

    @Override
    public void update(ProjectDTO.Request requestDTO) {

    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
