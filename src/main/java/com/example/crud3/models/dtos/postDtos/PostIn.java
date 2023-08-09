package com.example.crud3.models.dtos.postDtos;

import com.example.crud3.models.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostIn {
    private String title;
    private LocalDateTime publishDate;
    private Set<Long> tagIds = new HashSet<>();

    public PostEntity convertToEntity(PostEntity entity) {
        if (entity == null) {
            entity = new PostEntity();
        }
        entity.setTitle(title);
        entity.setPublishDate(publishDate);
        return entity;
    }
}
