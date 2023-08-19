package com.oss.gitborad.data.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

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
    @NotNull
    private String email;

    @Column(length = 45)
    @NotNull
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "TEXT", name = "git_url")
    @NotNull
    private String gitUrl;

    @Column(columnDefinition = "TEXT", name = "profile_image_url")
    @NotNull
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String token;
}
