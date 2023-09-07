package com.oss.gitboard.security.oauth;

import com.oss.gitboard.data.domain.User;
import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String username;
    private String email;
    private String url;
    private String avatar_url;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        System.out.println("User Attributes : " + attributes);
        if(registrationId.equals("github"))
            return ofGithub(userNameAttributeName, attributes);
        return null;
    }

    public static OAuthAttributes ofGithub(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name(attributes.get("name").toString())
                .email(attributes.get("email").toString())
                .username(attributes.get("login").toString())
                .url(attributes.get("url").toString())
                .avatar_url(attributes.get("avatar_url").toString())
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User saveUser() {
        return User.oauth2Register(
                name,
                email,
                username,
                url,
                avatar_url
        );
    }
}
