package com.oss.gitborad.data.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "depth")
    @NotNull
    private Integer depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category topCategory; //자기참조

}
