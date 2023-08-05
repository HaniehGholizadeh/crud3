package com.example.crud3.models.dtos.commentDtos;

import com.example.crud3.models.entities.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentIn {
    private String text;
    public CommentEntity convertToEntity(CommentEntity entity) {
        if (entity == null) {
            entity = new CommentEntity();
        }
        entity.setText(text);
        return entity;
    }
}
