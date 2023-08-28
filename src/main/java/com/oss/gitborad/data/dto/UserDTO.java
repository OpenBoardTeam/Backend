package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDTO {
    @Data
    @Builder
    @ToString
    static public class Info implements OAuth2User {

        final private String ROLE_PREFIX = "ROLE_";
        private User user;
        private Map<String, Object> attributes;

        @Override
        public Map<String, Object> getAttributes() {
            return this.attributes;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX+user.getRole()));
            return authorities;
        }

        @Override
        public String getName() {
            return user.getName();
        }
    }

    @Data
    @ToString
    @ApiModel("UserInfo")
    public static class InfoForAll {
        private Long id;
        private String name;
        private String email;
        private String description;
        private String gitUrl;
        private String imageUrl;
        private List<String> badgeList;
        private List<String> interestList;

        public InfoForAll(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.description = user.getDescription();
            this.gitUrl = user.getGitUrl();
            this.imageUrl = user.getImageUrl();
            this.badgeList = user.getBadgeList().stream()
                    .map(x -> x.getBadge().getName())
                    .collect(Collectors.toList());
            this.interestList = user.getInterestList().stream()
                    .map(x -> x.getCategory().getName())
                    .collect(Collectors.toList());

        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel("UserBadgeRequest")
    public static class BadgeRequest {
        private Long userId;
        private Long badgeId;
    }

}
