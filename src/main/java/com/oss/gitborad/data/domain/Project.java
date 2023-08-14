package com.oss.gitborad.data.domain;

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

    @Column(name = "name", columnDefinition = "text")
    private String name;

    @Column(name = "git_url", columnDefinition = "text")
    private String gitUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}