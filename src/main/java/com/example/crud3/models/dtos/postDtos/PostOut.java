package com.example.crud3.models.dtos.postDtos;

import com.example.crud3.models.dtos.commentDtos.CommentOut;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.models.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostOut {
    private Long id;
    private String title;
    private LocalDateTime publishDate;
    private LocalDateTime creationDate;
    private List<CommentOut> commentIds;
    private List<TagOut> tags;

    public PostOut(PostEntity entity) {
        if (entity != null) {
            id = entity.getId();
            title = entity.getTitle();
            creationDate = entity.getCreationDate();
            publishDate = entity.getPublishDate();
            if (Hibernate.isInitialized(entity.getComments())) {
                commentIds = entity.getComments().stream().map(CommentOut::new).toList();
            }
            if (Hibernate.isInitialized(entity.getTags())) {
                tags = entity.getTags().stream().map(TagOut::new).toList();
            }
        }
    }
}

