package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Bookmark;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

public class BookmarkDTO
{
    @Data
    @ToString
    @ApiModel("bookmarkInfo")
    public static class Info {
        private Long id;
        private String userName;
        private String projectName;

        private LocalDateTime createAT;
        private LocalDateTime updateAT;

        public Info(Bookmark bookmark){
            this.id = bookmark.getId();
            this.userName = bookmark.getUser().getName();
            this.projectName = bookmark.getProject().getName();

            this.createAT = bookmark.getCreatedAt();
            this.updateAT = bookmark.getUpdatedAt();
        }
    }

    @Data
    @ToString
    @ApiModel("bookmarkListInfo")
    public static class InfoForList {
        private Long id;
        private String projectName;
        private String projectDescription;
        private String projectGitUrl;
        private String projectUserName;

        private LocalDateTime createAT;
        private LocalDateTime updateAT;

        public InfoForList(Bookmark bookmark){
            this.id = bookmark.getId();
            this.projectName = bookmark.getProject().getName();
            this.projectDescription = bookmark.getProject().getDescription();
            this.projectGitUrl = bookmark.getProject().getGitUrl();
            this.projectUserName = bookmark.getProject().getUser().getName();

            this.createAT = bookmark.getCreatedAt();
            this.updateAT = bookmark.getUpdatedAt();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("bookmarkRequest")
    public static class Request {
        private Long userId;
        private Long projectId;
    }
}
