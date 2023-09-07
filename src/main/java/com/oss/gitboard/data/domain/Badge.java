package com.oss.gitboard.data.domain;

import com.oss.gitboard.data.dto.BadgeDTO;
import com.sun.istack.NotNull;
import lombok.*;

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

    @Builder
    public Badge(BadgeDTO.Request requestDTO, String imageUrl){
        this.name = requestDTO.getName();
        this.description = requestDTO.getDescription();
        this.imageUrl = imageUrl;

    }

}
