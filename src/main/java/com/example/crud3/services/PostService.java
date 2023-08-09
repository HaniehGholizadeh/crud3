package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.postDtos.PostIn;
import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import com.example.crud3.repositories.PostRepository;
import com.example.crud3.repositories.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class PostService {
    final PostRepository postRepository;
    final TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public PostOut create(PostIn model) throws CustomException {
        if (model.getPublishDate().isBefore(LocalDateTime.now())) {
            throw new CustomException("date and time is invalid", 1111, HttpStatus.BAD_REQUEST);
        }
        PostEntity postEntity = model.convertToEntity(new PostEntity());
        List<TagEntity> tags = tagRepository.findAllById(model.getTagIds());
        postEntity.setTags(new HashSet<>(tags));
        return new PostOut(postRepository.save(postEntity));
    }

    public void addTag(Long id, Long tagId) {
        check(id);
        tagRepository.addTagToPost(id, tagId);
    }

//    public void addTags(Long postId, List<Long> tagIds) {
//        tagRepository.addTagsToPost(postId, tagIds);
//    }

    public List<TagOut> getTags(Long id) {
        check(id);
        Set<TagEntity> tags = tagRepository.getTagsByPostId(id);
        return tags.stream().map(TagOut::new).toList();
    }

    public void deleteById(Long id) {
        check(id);
        postRepository.deleteById(id);
    }

    public List<PostOut> getAllPosts() throws CustomException {
        List<PostOut> postOuts = postRepository.findAll().stream().map(PostOut::new).toList();
        if (postOuts.isEmpty()) {
            throw new CustomException("There is no exist any post", 1002, HttpStatus.NOT_FOUND);
        }
        return postOuts;
    }


    public PostOut getPost(Long id) {
        check(id);
        return new PostOut(postRepository.findPostEntityById(id));
    }

    private void check(Long id) throws CustomException {
        if (!postRepository.existsById(id)) {
            throw new CustomException("Post not found", 1001, HttpStatus.NOT_FOUND);
        }
    }
}
