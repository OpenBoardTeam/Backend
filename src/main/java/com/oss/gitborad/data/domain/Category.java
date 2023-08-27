package com.oss.gitborad.data.domain;

import com.sun.istack.NotNull;
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
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name="category_group_id")
    private CategoryGroup group;

    @Builder
    public Category(String name, CategoryGroup group){
        this.name = name;
        this.group = group;
    }
}
