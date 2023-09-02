package com.oss.gitborad.security.oauth;

import com.oss.gitborad.data.domain.User;
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
    private String email;
    private String url;
    private String avatar_url;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        System.out.println("User Attributes : " + attributes);
        if(registrationId.equals("github"))
            return ofGithub(userNameAttributeName, attributes);
        return null;
    }
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes, String email) {
        System.out.println("User Attributes : " + attributes);
        if(registrationId.equals("github"))
            return ofGithub(userNameAttributeName, attributes, email);
        return null;
    }

    public static OAuthAttributes ofGithub(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name(attributes.get("name").toString())
                .email(attributes.get("email").toString())
                .url(attributes.get("url").toString())
                .avatar_url(attributes.get("avatar_url").toString())
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    public static OAuthAttributes ofGithub(String userNameAttributeName, Map<String, Object> attributes, String email) {
        return OAuthAttributes.builder()
                .name(attributes.get("name").toString())
                .email(email)
                .url(attributes.get("url").toString())
                .avatar_url(attributes.get("avatar_url").toString())
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User saveUser() {
        return User.oauth2Register(
                name,
                email,
                url,
                avatar_url
        );
    }
}
