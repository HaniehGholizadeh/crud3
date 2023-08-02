package com.example.crud3.controllers;

import com.example.crud3.models.dtos.postDtos.PostIn;
import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {
    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public ResponseEntity<PostOut> create(@RequestBody PostIn model, BindingResult bindingResult) {
        return new ResponseEntity<>(postService.create(model), HttpStatus.OK);
    }
}
