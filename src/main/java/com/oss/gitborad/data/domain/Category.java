package com.oss.gitborad.data.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "category")
public class Category extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @ManyToOne
    @JoinColumn(name="category_group_id")
    private CategoryGroup group;

    @Builder
    public Category(String name, User writer, CategoryGroup group){
        this.name = name;
        this.writer = writer;
        this.group = group;
    }
}
