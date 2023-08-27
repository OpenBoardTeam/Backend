package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Badge;
import com.oss.gitborad.data.domain.Interest;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

public class BadgeDTO {

    @Data
    @ToString
    @ApiModel("BadgeInfo")
    public static class info{
        private Long id;
        private String name;
        private String description;
        private String imageUrl;

        public info(Badge badge){
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
    public static class request{
        private String name;
        private String description;
        private String imageUrl;
    }

}
