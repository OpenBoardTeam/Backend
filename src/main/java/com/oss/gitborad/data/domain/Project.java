package com.oss.gitborad.data.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "project")
public class Project extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(length = 45)
    @NotNull
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "git_url", columnDefinition = "text")
    @NotNull
    private String gitUrl;

    @Column(length = 20)
    @NotNull
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}