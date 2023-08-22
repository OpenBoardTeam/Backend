package com.oss.gitborad.data.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "category_group")
public class CategoryGroup extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_group_id")
    private Long id;

    @Column(length = 20)
    @NotNull
    private String name;

    @Builder
    public CategoryGroup(String name){
        this.name = name;
    }
}
