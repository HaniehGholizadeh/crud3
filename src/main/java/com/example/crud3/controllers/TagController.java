package com.example.crud3.controllers;

import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.models.dtos.tagDtos.TagIn;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/tags")
public class TagController {
    final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("")
    public ResponseEntity<TagOut> create(@Valid @RequestBody TagIn model) {
        return new ResponseEntity<>(tagService.create(model), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagOut> getById(@PathVariable Long id) {
        return new ResponseEntity<>(tagService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TagOut>> getAll() {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        tagService.deleteById(id);
    }

    @GetMapping("/{id}/post")
    public ResponseEntity<List<PostOut>> getPosts(@PathVariable Long id) {
        return new ResponseEntity<>(tagService.getPosts(id), HttpStatus.OK);
    }
}
