package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.postDtos.PostIn;
import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import com.example.crud3.repositories.PostRepository;
import com.example.crud3.repositories.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service

public class PostService {
    final PostRepository postRepository;
    final TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public PostOut create(PostIn model) {
        PostEntity postEntity = model.convertToEntity(new PostEntity());
        postRepository.save(postEntity);
        return new PostOut(postEntity);
    }

    public void addTag(Long id, Long tagId) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new CustomException("Post not found", 1004, HttpStatus.NOT_FOUND));
        TagEntity tagEntity = tagRepository.findById(tagId).orElseThrow(() -> new CustomException("Tag not found", 1006, HttpStatus.NOT_FOUND));
        postEntity.getTags().add(tagEntity);
//        postRepository.save(postEntity);
        tagRepository.save(tagEntity);
//
//        tagEntity.getPosts().add(postEntity);
//        postRepository.save(postEntity);
//        tagRepository.save(tagEntity);
    }
}
