package com.example.crud3.models.dtos.tagDtos;

import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TagOut {
    private Long id;
    private String name;
    private Set<Long> postsId;

    public TagOut(TagEntity entity) {
        if (entity != null) {
            id = entity.getId();
            name = entity.getName();
            postsId = entity.getPosts().stream().map(PostEntity::getId).collect(Collectors.toSet());
        }
    }
}
