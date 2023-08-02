package com.example.crud3.services;

import com.example.crud3.models.dtos.tagDtos.TagIn;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.models.entities.TagEntity;
import com.example.crud3.repositories.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagOut create(TagIn model) {
        TagEntity tagEntity = model.convertToEntity(new TagEntity());
        tagRepository.save(tagEntity);
        return new TagOut(tagEntity);
    }
}
