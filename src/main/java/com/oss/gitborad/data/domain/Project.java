package com.oss.gitborad.data.domain;

import com.sun.istack.NotNull;
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

    @Column(name = "owner_url", columnDefinition = "text")
    @NotNull
    private String ownerUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ProjectCategory> categoryList = new ArrayList<>();

    @Builder
    public Project(String name, String description, String gitUrl, String type, String ownerUrl, User user){
        this.name = name;
        this.description = description;
        this.gitUrl = gitUrl;
        this.type = type;
        this.ownerUrl = ownerUrl;
        this.user = user;
    }
}