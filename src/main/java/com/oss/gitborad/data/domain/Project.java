package com.oss.gitborad.data.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String simple_description;

    @Column(name = "git_url", columnDefinition = "text")
    private String gitUrl;

    @Column(length = 20)
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ProjectCategory> categoryList = new ArrayList<>();

    @Builder
    public Project(String name, String description, String simple_description, String gitUrl, String type, String ownerUrl, User user){
        this.name = name;
        this.description = description;
        this.simple_description = simple_description;
        this.gitUrl = gitUrl;
        this.type = type;
        this.user = user;
    }
}