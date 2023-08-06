package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.models.dtos.tagDtos.TagIn;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.models.entities.TagEntity;
import com.example.crud3.repositories.PostRepository;
import com.example.crud3.repositories.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    final TagRepository tagRepository;
    final PostRepository postRepository;

    public TagService(TagRepository tagRepository, PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    public TagOut create(TagIn model) {
        TagEntity tagEntity = model.convertToEntity(new TagEntity());
        return new TagOut(tagRepository.save(tagEntity));
    }

    public TagOut getById(Long id) {
        check(id);
        return new TagOut(tagRepository.findById(id).get());
    }

    public List<TagOut> getAllTags() {
        return tagRepository.findAll().stream().map(TagOut::new).toList();
    }

    public void deleteById(Long id) {
        check(id);
        tagRepository.deleteById(id);
    }

    public List<PostOut> getPosts(Long id) {
        check(id);
        return postRepository.findPostsByTagId(id).stream().map(PostOut::new).toList();
    }

    private void check(Long id) throws CustomException {
        if (!tagRepository.existsById(id)) {
            throw new CustomException("Tag not found", 1002, HttpStatus.NOT_FOUND);
        }
    }
}
