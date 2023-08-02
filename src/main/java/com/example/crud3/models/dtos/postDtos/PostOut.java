package com.example.crud3.models.dtos.postDtos;

import com.example.crud3.models.dtos.commentDtos.CommentOut;
import com.example.crud3.models.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostOut {
    private Long id;
    private String title;
    private LocalDateTime date;
    private Set<CommentOut> comments;
    private Set<Long> commentId;

    public PostOut(PostEntity entity) {
        if (entity != null) {
            title = entity.getTitle();
            date = entity.getDate();
            id = entity.getId();
        }
    }
}
