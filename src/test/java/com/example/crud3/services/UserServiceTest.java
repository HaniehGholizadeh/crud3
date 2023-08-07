package com.example.crud3.services;

import com.example.crud3.models.dtos.profileDtos.ProfileIn;
import com.example.crud3.models.dtos.userDtos.UserIn;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Mock
    ProfileIn profileIn;

    @Test
    void createUserWhenPasswordIsNotValid() {
        assertThrows(ConstraintViolationException.class, () -> {
            final UserIn userIn = new UserIn(anyString(), "pass998H", anyString(), profileIn);
            userService.createUser(userIn);
        });
    }
}