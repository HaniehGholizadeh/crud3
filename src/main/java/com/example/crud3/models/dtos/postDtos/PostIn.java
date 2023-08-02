package com.example.crud3.models.dtos.postDtos;

import com.example.crud3.models.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostIn {
    private String title;

    public PostEntity convertToEntity(PostEntity entity) {
        if (entity == null) {
            entity = new PostEntity();
        }
        entity.setTitle(title);
        return entity;
    }
}
