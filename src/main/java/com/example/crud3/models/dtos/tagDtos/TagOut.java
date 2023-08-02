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

public class TagOut {
    private String result;

    public TagOut(TagEntity entity) {
        if (entity != null) {
            result = String.format("%s tag with id=%d added!", entity.getName(), entity.getId());
        }
    }
}
