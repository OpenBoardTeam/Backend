package com.oss.gitboard.data.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "hashtag")
public class Hashtag extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @Column
    private boolean certified;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @ManyToOne
    @JoinColumn(name="hashtag_group_id")
    private HashtagGroup group;

    @Builder
    public Hashtag(String name, boolean certified, User writer, HashtagGroup group){
        this.name = name;
        this.certified = certified;
        this.writer = writer;
        this.group = group;
    }
}
