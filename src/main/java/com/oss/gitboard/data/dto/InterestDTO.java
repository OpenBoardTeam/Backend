package com.oss.gitboard.data.dto;

import com.oss.gitboard.data.domain.Interest;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

public class InterestDTO {

    @Data
    @ToString
    @ApiModel("InterestInfo")
    public static class Info {
        private Long id;
        private String user;
        private String hashtag;

        public Info(Interest interest){
            this.id = interest.getId();
            this.user = interest.getUser().getName();
            this.hashtag = interest.getHashtag().getName();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel("InterestRequest")
    public static class Request {
        private List<String> hashtagList;
    }

}
