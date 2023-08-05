package com.example.crud3.models.dtos.postDtos;

import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.models.entities.CommentEntity;
import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostOut {
    private Long id;
    private String title;
    private LocalDateTime date;
    private Set<Long> commentIds;
    private Set<Long> tagIds;
//    private Set<TagOut> tags;

    public PostOut(PostEntity entity) {
        if (entity != null) {
            title = entity.getTitle();
            date = entity.getDate();
            id = entity.getId();
            commentIds = entity.getComments().stream().map(CommentEntity::getId).collect(Collectors.toSet());
            tagIds = entity.getTags().stream().map(TagEntity::getId).collect(Collectors.toSet());
        }
    }
}
