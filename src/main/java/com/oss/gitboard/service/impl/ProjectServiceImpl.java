package com.oss.gitboard.service.impl;

import com.oss.gitboard.data.domain.Hashtag;
import com.oss.gitboard.data.domain.Project;
import com.oss.gitboard.data.domain.ProjectHashtag;
import com.oss.gitboard.data.domain.User;
import com.oss.gitboard.data.dto.ProjectDTO;
import com.oss.gitboard.data.dto.UserDTO;
import com.oss.gitboard.data.model.GitHubRepositoryModel;
import com.oss.gitboard.repository.HashtagRepository;
import com.oss.gitboard.repository.ProjectHashtagRepository;
import com.oss.gitboard.repository.ProjectRepository;
import com.oss.gitboard.repository.UserRepository;
import com.oss.gitboard.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final ProjectHashtagRepository projectHashtagRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, HashtagRepository hashtagRepository, ProjectHashtagRepository projectHashtagRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.hashtagRepository = hashtagRepository;
        this.projectHashtagRepository = projectHashtagRepository;
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
    public List<ProjectDTO.CardInfo> findAll(int number, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(number, size, sort);
        Page<ProjectDTO.CardInfo> response = projectRepository.findAll(pageable).map(ProjectDTO.CardInfo::new);

        List<ProjectDTO.CardInfo> pageRequestDTO = new ArrayList<>();
        for (ProjectDTO.CardInfo i : response){
            pageRequestDTO.add(i);
        }
        return pageRequestDTO;
    }

    @Override
    public List<ProjectDTO.CardInfo> findListBySearch(int number, int size, String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(number, size, sort);
        Page<ProjectDTO.CardInfo> response = projectRepository.findByNameContainingOrDescriptionContaining(keyword, keyword, pageable).map(ProjectDTO.CardInfo::new);

        List<ProjectDTO.CardInfo> pageRequestDTO = new ArrayList<>();
        for (ProjectDTO.CardInfo i : response){
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
                .simpleDescription(requestDTO.getSimpleDescription())
                .gitUrl(requestDTO.getGitUrl())
                .user(user)
                .build();

        for(String i : requestDTO.getHashtagList()){
            Hashtag hashtag = hashtagRepository.findByName(i);

            ProjectHashtag projectHashtag = ProjectHashtag.builder()
                    .project(project)
                    .hashtag(hashtag)
                    .build();

            projectRepository.save(project);
            projectHashtagRepository.save(projectHashtag);

        }

        //TODO: 저장 후 반환하는 Info 정보에 카테고리 데이터가 반영이 안되어있는 버그 수정 필요
        ProjectDTO.Info findOneDto = new ProjectDTO.Info(project);

        return findOneDto;
    }

    @Override
    public ProjectDTO.ResponseBasicInfo getBasicInfo(String url) {
            WebClient webClient = WebClient.create("https://api.github.com/repos");
            String uri = getRepositoryUri(url);
        log.info("변환된 URI: {}", uri);

            assert uri != null;

            ResponseEntity<GitHubRepositoryModel> block = webClient.get()
                    .uri(uri)
                    .retrieve()
                    .toEntity(GitHubRepositoryModel.class)
                    .block();
            log.info("블록: {}", block);

            assert block != null; // Throw exception when block is null. (Unexpected url)

            GitHubRepositoryModel model = block.getBody();
            log.info("모델: {}",model);
            assert model != null;

            return ProjectDTO.ResponseBasicInfo.builder()
                    .owner(model.owner)
                    .projectName(model.name)
                    .simpleDescription(model.description)
                    .contributorsUrl(model.contributors_url)
                    .build();
    }

    @Override
    public void delete(Long id, UserDTO.Info principal) {
        Project project = projectRepository.findById(id).orElse(null);
        if(project == null) return;
        Long projectId = project.getUser().getId();
        Long userId = principal.getUser().getId();
        if(projectId.equals(userId)) {
            projectRepository.deleteById(id);
        }
    }

    private String getRepositoryUri(String url) {
        String uri = url;
        // Link should start with "https://github.com"
        log.info("url: {}", url);
        if(!uri.startsWith("https://github.com")) {
            log.info("Url doesn't starts with \"prefix\"");
            return null;
        }
        uri = uri.replace("https://github.com", "");
        // Eliminate ".git"
        if(uri.endsWith(".git")) {
            uri = uri.replace(".git", "");
        }
        return uri;
    }
}
