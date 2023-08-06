package com.example.crud3.models.dtos.tagDtos;

import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TagOut {
    private Long id;
    private String name;
    private List<Long> postIds;

    public TagOut(TagEntity entity) {
        if (entity != null) {
            id = entity.getId();
            name = entity.getName();
            if (Hibernate.isInitialized(entity.getPosts()))
                postIds = entity.getPosts().stream().map(PostEntity::getId).toList();
        }
    }
}
