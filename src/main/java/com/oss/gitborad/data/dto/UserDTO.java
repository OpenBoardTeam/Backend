package com.oss.gitborad.data.dto;

import com.oss.gitborad.data.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UserDTO {
    @Data
    @Builder
    @ToString
    static public class Info implements OAuth2User {

        private String ROLE_PREFIX = "ROLE_";
        private User user;
        private Map<String, Object> attributes;

        @Override
        public Map<String, Object> getAttributes() {
            return this.attributes;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX+"USER"));
            return authorities;
        }

        @Override
        public String getName() {
            return user.getName();
        }
    }
}
