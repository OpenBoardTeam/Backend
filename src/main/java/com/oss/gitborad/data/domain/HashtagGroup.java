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
@Table(name = "hashtag_group")
public class HashtagGroup extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_group_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Hashtag> hashtagList = new ArrayList<>();


    @Builder
    public HashtagGroup(String name){
        this.name = name;
    }
}
