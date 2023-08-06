package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.postDtos.PostIn;
import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.repositories.PostRepository;
import com.example.crud3.repositories.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
        postEntity = postRepository.save(postEntity);
        tagRepository.addTagsToPost(postEntity.getId(), model.getTagIds());
        return new PostOut(postEntity);
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
        return tagRepository.findTagsByPostId(id).stream().map(TagOut::new).toList();
    }

    public void deleteById(Long id) {
        check(id);
        postRepository.deleteById(id);
    }

    public List<PostOut> getAllPosts() {
        return postRepository.findAll().stream().map(PostOut::new).toList();
    }

    private void check(Long id) throws CustomException {
        if (!postRepository.existsById(id)) {
            throw new CustomException("Post not found", 1001, HttpStatus.NOT_FOUND);
        }
    }
}
