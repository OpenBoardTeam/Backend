package com.oss.gitborad.data.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 45)
    private String email;

    @Column(length = 45)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "TEXT", name = "git_url")
    private String gitUrl;

    @Column(columnDefinition = "TEXT", name = "profile_image_url")
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String token;

    @Column(columnDefinition = "varchar(45)", nullable = false)
    private String role = "USER";

    @ToString.Exclude
    @OneToMany(mappedBy = "badge", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserBadge> badgeList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "hashtag", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Interest> interestList = new ArrayList<>();


    static public User oauth2Register(
            String name,
            String email,
            String url,
            String avatar_url
    ) {
        User user = new User();
        user.name = name;
        user.email = email;
        user.gitUrl = url;
        user.imageUrl = avatar_url;
        return user;
    }
}
