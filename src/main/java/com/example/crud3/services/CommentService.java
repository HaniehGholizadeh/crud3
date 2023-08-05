package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.commentDtos.CommentIn;
import com.example.crud3.models.dtos.commentDtos.CommentOut;
import com.example.crud3.models.entities.CommentEntity;
import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.repositories.CommentRepository;
import com.example.crud3.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CommentService {
    final CommentRepository commentRepository;
    final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    public CommentOut create(Long postId, CommentIn model) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new CustomException("Post not found", 1004, HttpStatus.NOT_FOUND));
        CommentEntity commentEntity = model.convertToEntity(new CommentEntity());
        commentEntity.setPost(postEntity);
        commentRepository.save(commentEntity);
        return new CommentOut(commentEntity);
    }

    public void delete(Long id, Long postId) {
        Optional<CommentEntity> commentEntity = commentRepository.findByIdAndPostId(id, postId);
        if (commentEntity.isEmpty()) {
            throw new CustomException("comment not found", 1005, HttpStatus.NOT_FOUND);
        }
        commentRepository.delete(commentEntity.get());
    }

    public void update(Long id, CommentIn model) {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(() -> new CustomException("Comment not found", 1011, HttpStatus.NOT_FOUND));
        CommentEntity updatedComment = model.convertToEntity(commentEntity);
        commentRepository.save(updatedComment);
    }
}
