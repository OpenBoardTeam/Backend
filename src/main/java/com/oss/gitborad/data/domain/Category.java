package com.oss.gitborad.data.domain;

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

    @Column(length = 20, name = "classify")
    private String classify;

    @Column(length = 40, name = "name")
    private String name;
}
