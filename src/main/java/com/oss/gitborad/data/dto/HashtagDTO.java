package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Hashtag;
import com.oss.gitborad.data.domain.HashtagGroup;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Category, Category Group DTO
 */
public class HashtagDTO {

    //Category
    @Data
    @ToString
    @ApiModel("HashtagInfo")
    public static class Info {
        private Long id;
        private String name;
        private String writerName;
        private String groupName;
        private boolean accredit;
        private LocalDateTime createdAt;
        private LocalDateTime updateAT;

        public Info(Hashtag hashtag){
            this.id = hashtag.getId();
            this.name = hashtag.getName();
            this.writerName = hashtag.getWriter().getName();
            this.groupName = hashtag.getGroup().getName();
            this.accredit = hashtag.isCertified();
            this.createdAt = hashtag.getCreatedAt();
            this.updateAT = hashtag.getUpdatedAt();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("HashtagRequest")
    public static class Request {
        @NotNull
        private String name;
        @NotNull
        private long writerId;
        @NotNull
        private long groupId;
        @NotNull
        private boolean certified;
    }

    //Category Group
    @Data
    @ToString
    @ApiModel("HashtagGroupInfo")
    public static class GroupInfo {
        private long id;
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime updateAT;

        public GroupInfo(HashtagGroup hashtagGroup){
            this.id = hashtagGroup.getId();
            this.name = hashtagGroup.getName();
            this.createdAt = hashtagGroup.getCreatedAt();
            this.updateAT = hashtagGroup.getUpdatedAt();
        }
    }

    @Data
    @ToString
    @ApiModel("HashtagGroupList")
    public static class GroupListInfo{
        private List<String> certifiedList;
        private List<String> uncertifiedList;

        public GroupListInfo(HashtagGroup hashtagGroup){
            this.certifiedList = hashtagGroup.getHashtagList().stream()
                    .filter(x -> x.isCertified() == true)
                    .map(x -> x.getName())
                    .collect(Collectors.toList());
            this.uncertifiedList = hashtagGroup.getHashtagList().stream()
                    .filter(x -> x.isCertified() == false)
                    .map(x -> x.getName())
                    .collect(Collectors.toList());
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("HashtagGroupRequest")
    public static class GroupRequest {
        private String name;
    }




}
