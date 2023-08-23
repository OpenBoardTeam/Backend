package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Interest;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

public class InterestDTO {

    @Data
    @ToString
    @ApiModel("InterestInfo")
    public static class info{
        private Long id;
        private String user;
        private String Category;

        public info(Interest interest){
            this.id = interest.getId();
            this.user = interest.getUser().getName();
            this.Category = interest.getCategory().getName();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel("InterestRequest")
    public static class request{
        private Long userId;
        private List<String> categories;
    }

}
