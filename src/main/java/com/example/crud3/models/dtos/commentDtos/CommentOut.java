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
public class CommentOut {
    private Long id;
    private String text;

    public CommentOut(CommentEntity entity) {
        if (entity != null) {
            id = entity.getId();
            text = entity.getText();
        }
    }
}
