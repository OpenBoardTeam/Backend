package com.oss.gitborad.data.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "project_hashtag")
public class ProjectHashtag extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_hashtag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    @Builder
    public ProjectHashtag(Project project, Hashtag hashtag){
        this.project = project;
        this.hashtag = hashtag;
    }
}
