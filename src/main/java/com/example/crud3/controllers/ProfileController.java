package com.example.crud3.controllers;

import com.example.crud3.models.dtos.profileDtos.ProfileEditIn;
import com.example.crud3.models.dtos.profileDtos.ProfileIn;
import com.example.crud3.models.dtos.profileDtos.ProfileOut;
import com.example.crud3.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("")
    public ResponseEntity<ProfileOut> create(@Valid @RequestBody ProfileIn model, BindingResult bindingResult) {
        return new ResponseEntity<>(profileService.create(model), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileOut> update(@PathVariable Long id, @Valid @RequestBody ProfileEditIn model, BindingResult bindingResult) {
        return new ResponseEntity<>(profileService.update(id, model), HttpStatus.OK);
    }
}
