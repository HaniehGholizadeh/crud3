package com.example.crud3.controllers;

import com.example.crud3.models.dtos.tagDtos.TagIn;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.services.TagService;
import org.hibernate.mapping.TableOwner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/tags")
public class TagController {
    final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("")
    public ResponseEntity<TagOut> create(@Valid @RequestBody TagIn model){
        return new ResponseEntity<>(tagService.create(model), HttpStatus.OK);
    }
}
