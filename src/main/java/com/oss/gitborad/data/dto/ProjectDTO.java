package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Project;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class ProjectDTO {

    @Data
    @ToString
    @ApiModel("ProjectInfo")
    public static class Info {
        private Long id;
        private String name;
        private String description;
        private String simple_description;
        private String gitUrl;
        private String ownerUrl;
        private String userName;
        private List<String> categories;
        private List<String> framework;
        private List<String> language;
        private LocalDateTime createdAt;
        private LocalDateTime updateAT;

        public Info(Project project){
            this.id = project.getId();
            this.name = project.getName();
            this.description = project.getDescription();
            this.simple_description = project.getSimple_description();
            this.gitUrl = project.getGitUrl();
            this.ownerUrl = project.getOwnerUrl();
            this.userName = project.getUser().getName();
            this.categories = project.getCategoryList().stream()
                    .filter(x -> x.getCategory().getGroup().getName().equals("categories"))
                    .map(x -> x.getCategory().getName())
                    .collect(Collectors.toList());
            this.framework = project.getCategoryList().stream()
                    .filter(x -> x.getCategory().getGroup().getName().equals("framework"))
                    .map(x -> x.getCategory().getName())
                    .collect(Collectors.toList());
            this.language = project.getCategoryList().stream()
                    .filter(x -> x.getCategory().getGroup().getName().equals("language"))
                    .map(x -> x.getCategory().getName())
                    .collect(Collectors.toList());
            this.createdAt = project.getCreatedAt();
            this.updateAT = project.getUpdatedAt();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel("ProjectRequest")
    public static class Request {
        private String name;
        private String description;
        private String gitUrl;
        private String ownerUrl;
        private Long userId;
        private List<String> categories;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel("ProjectCategoryRequest")
    public static class CategoryRequest {
        private Long projectId;
        private Long categoryId;
    }


}
