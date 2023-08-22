package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.Category;
import com.oss.gitborad.data.domain.CategoryGroup;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Category, Category Group DTO
 */
public class CategoryDTO {

    //Category
    @Data
    @ToString
    @ApiModel("categoryInfo")
    public static class info{
        private Long id;
        private String name;
        private String groupName;
        private LocalDateTime createdAt;
        private LocalDateTime updateAT;

        public info(Category category){
            this.id = category.getId();
            this.name = category.getName();
            this.groupName = category.getGroup().getName();
            this.createdAt = category.getCreatedAt();
            this.updateAT = category.getUpdatedAt();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("categoryRequest")
    public static class request{
        @NonNull
        private String name;
        @NonNull
        private long groupId;
    }

    //Category Group
    @Data
    @ToString
    @ApiModel("CategoryGroupInfo")
    public static class groupInfo{
        private long id;
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime updateAT;

        public groupInfo(CategoryGroup categoryGroup){
            this.id = categoryGroup.getId();
            this.name = categoryGroup.getName();
            this.createdAt = categoryGroup.getCreatedAt();
            this.updateAT = categoryGroup.getUpdatedAt();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("CategoryGroupRequest")
    public static class groupRequest{
        private String name;
    }


}
