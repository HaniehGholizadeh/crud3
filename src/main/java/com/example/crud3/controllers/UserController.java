package com.example.crud3.controllers;

import com.example.crud3.models.dtos.userDtos.UserEditIn;
import com.example.crud3.models.dtos.userDtos.UserIn;
import com.example.crud3.models.dtos.userDtos.UserOut;
import com.example.crud3.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOut> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserOut>> getAll() {
        return new ResponseEntity<>(userService.getALl(), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    @PostMapping("")
    public ResponseEntity<UserOut> create(@Valid @RequestBody UserIn model, BindingResult bindingResult) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return new ResponseEntity<>(userService.create(model), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOut> update(@PathVariable Long id, @Valid @RequestBody UserEditIn model, BindingResult bindingResult) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return new ResponseEntity<>(userService.update(id, model), HttpStatus.OK);
    }
}