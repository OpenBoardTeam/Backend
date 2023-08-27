package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Badge;
import io.swagger.annotations.ApiModel;
import lombok.*;

public class BadgeDTO {

    @Data
    @ToString
    @ApiModel("BadgeInfo")
    public static class Info {
        private Long id;
        private String name;
        private String description;
        private String imageUrl;

        public Info(Badge badge){
            this.id = badge.getId();
            this.name = badge.getName();
            this.description = badge.getDescription();
            this.imageUrl = badge.getImageUrl();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel("BadgeRequest")
    public static class Request {
        private String name;
        private String description;
        private String imageUrl;
    }

}
