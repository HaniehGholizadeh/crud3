package com.example.crud3.controllers;

import com.example.crud3.models.dtos.postDtos.PostIn;
import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/posts")
public class PostController {
    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public ResponseEntity<PostOut> create(@Valid @RequestBody PostIn model, BindingResult bindingResult) {
        return new ResponseEntity<>(postService.create(model), HttpStatus.OK);
    }

    @PostMapping("/{id}/addTag/{tagId}")
    public void addTag(@PathVariable Long id, @PathVariable Long tagId) {
        postService.addTag(id, tagId);
    }

    @GetMapping("/{id}/tags")
    public ResponseEntity<List<String>> getTags(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getTags(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }

}
