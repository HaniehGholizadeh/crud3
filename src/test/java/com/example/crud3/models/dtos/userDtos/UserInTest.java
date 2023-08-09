package com.example.crud3.models.dtos.userDtos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserInTest {

    Set<ConstraintViolation<UserIn>> constraintViolations;
    @Spy
    @InjectMocks
    UserIn userIn;

    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        userIn = new UserIn();
    }

    @Test
    public void emailFieldInvalid() {
        userIn.setEmail("wrong.com");
        constraintViolations = validator.validate(userIn);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void emailFieldValid() {
        userIn.setEmail("test@gmail.com");
        constraintViolations = validator.validate(userIn);
        assertThat(constraintViolations.size()).isEqualTo(0);
    }
}