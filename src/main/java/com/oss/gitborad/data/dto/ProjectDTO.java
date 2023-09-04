package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Project;
import com.oss.gitborad.data.model.Owner;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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
        private String userName;
        private List<String> Group;
        private List<String> Social;
        private List<String> Language;
        private List<String> Framework;
        private List<String> Infra;
        private LocalDateTime createdAt;
        private LocalDateTime updateAT;

        public Info(Project project){

            this.id = project.getId();
            this.name = project.getName();
            this.description = project.getDescription();
            this.simple_description = project.getSimple_description();
            this.gitUrl = project.getGitUrl();
            this.userName = project.getUser().getName();
            //TODO: 해시태그 리스트 출력 반복문으로 간소화

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
        private String simple_description;
        private String gitUrl;
        private Long userId;
        private List<String> hashtagList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel("ProjectResponseBasicInfo")
    public static class ResponseBasicInfo {
        private String projectName;
        private String simpleDescription;
        private String description;
        private Owner owner;
        private String contributorsUrl;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel("ProjectHashtagRequest")
    public static class HashtagRequest {
        private Long projectId;
        private Long hashtagId;
    }


}
