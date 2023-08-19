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
@Table(name = "badge")
public class Badge extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long id;

    @Column(length = 40)
    @NotNull
    private String name;

    @Column(columnDefinition = "text")
    @NotNull
    private String description;

    @Column(columnDefinition = "TEXT", name = "image_url")
    @NotNull
    private String imageUrl;


}
