package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Project;
import com.oss.gitborad.data.model.Owner;
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
        private String simpleDescription;
        private String gitUrl;
        private String userName;

        private List<String> group;
        private List<String> social;
        private List<String> language;
        private List<String> framework;
        private List<String> infra;

        private LocalDateTime createdAt;
        private LocalDateTime updateAT;

        public Info(Project project){

            this.id = project.getId();
            this.name = project.getName();
            this.description = project.getDescription();
            this.simpleDescription = project.getSimpleDescription();
            this.gitUrl = project.getGitUrl();
            this.userName = project.getUser().getName();
            this.group= project.getHashtagList().stream()
                    .filter(x -> x.getHashtag().getGroup().getName().equals("Group"))
                    .map(x -> x.getHashtag().getName())
                    .collect(Collectors.toList());
            this.social= project.getHashtagList().stream()
                    .filter(x -> x.getHashtag().getGroup().getName().equals("Social"))
                    .map(x -> x.getHashtag().getName())
                    .collect(Collectors.toList());
            this.language= project.getHashtagList().stream()
                    .filter(x -> x.getHashtag().getGroup().getName().equals("Language"))
                    .map(x -> x.getHashtag().getName())
                    .collect(Collectors.toList());
            this.framework= project.getHashtagList().stream()
                    .filter(x -> x.getHashtag().getGroup().getName().equals("Framework"))
                    .map(x -> x.getHashtag().getName())
                    .collect(Collectors.toList());
            this.infra= project.getHashtagList().stream()
                    .filter(x -> x.getHashtag().getGroup().getName().equals("Infra"))
                    .map(x -> x.getHashtag().getName())
                    .collect(Collectors.toList());
            this.createdAt = project.getCreatedAt();
            this.updateAT = project.getUpdatedAt();
        }
    }

    @Data
    @ToString
    @ApiModel("ProjectCardInfo")
    public static class CardInfo {
        private Long id;
        private String name;
        private String simpleDescription;
        private List<String> hashtagList;
        private LocalDateTime createdAt;
        private LocalDateTime updateAT;

        public CardInfo(Project project){

            this.id = project.getId();
            this.name = project.getName();
            this.simpleDescription = project.getSimpleDescription();
            this.hashtagList= project.getHashtagList().stream()
                    .map(x -> x.getHashtag().getName())
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
        private String simpleDescription;
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
