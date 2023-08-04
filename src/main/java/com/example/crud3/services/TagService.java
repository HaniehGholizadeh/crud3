package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.tagDtos.TagIn;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import com.example.crud3.repositories.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public TagOut getById(Long id) {
        return new TagOut(tagRepository.findById(id).orElseThrow(() -> new CustomException("Tag not found", 1008, HttpStatus.NOT_FOUND)));
    }

    public List<String> getALl() {
        return tagRepository.findAll().stream().map(TagEntity::getName).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        TagEntity tag = tagRepository.findById(id).orElseThrow(() -> new CustomException("Tag not found", 1009, HttpStatus.NOT_FOUND));
        tag.getPosts().forEach(postEntity -> postEntity.getTags().remove(tag));
        tagRepository.delete(tag);
    }

    public List<String> getPosts(Long id) {
        TagEntity tag = tagRepository.findById(id).orElseThrow(() -> new CustomException("Tag not found", 1011, HttpStatus.NOT_FOUND));
        return tag.getPosts().stream().map(PostEntity::getTitle).collect(Collectors.toList());
    }
}
