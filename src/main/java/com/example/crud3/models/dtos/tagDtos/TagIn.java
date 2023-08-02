package com.example.crud3.models.dtos.tagDtos;

import com.example.crud3.models.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TagIn {
    private String name;

    public TagEntity convertToEntity(TagEntity entity) {
        if (entity == null) {
            entity = new TagEntity();
        }
        entity.setName(name);
        return entity;
    }
}
