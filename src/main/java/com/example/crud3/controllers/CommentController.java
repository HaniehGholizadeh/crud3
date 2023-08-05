package com.example.crud3.controllers;

import com.example.crud3.models.dtos.commentDtos.CommentIn;
import com.example.crud3.models.dtos.commentDtos.CommentOut;
import com.example.crud3.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public ResponseEntity<CommentOut> create(@PathVariable Long postId, @Valid @RequestBody CommentIn model) {
        return new ResponseEntity<>(commentService.create(postId, model), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id, @PathVariable Long postId) {
        commentService.delete(id, postId);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, CommentIn model) {
        commentService.update(id, model);
    }
}
