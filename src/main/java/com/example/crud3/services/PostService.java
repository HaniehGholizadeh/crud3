package com.example.crud3.services;

import com.example.crud3.models.dtos.postDtos.PostIn;
import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service

public class PostService {
    final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostOut create(PostIn model) {
        PostEntity postEntity = model.convertToEntity(new PostEntity());
        postRepository.save(postEntity);
        return new PostOut(postEntity);
    }
}
