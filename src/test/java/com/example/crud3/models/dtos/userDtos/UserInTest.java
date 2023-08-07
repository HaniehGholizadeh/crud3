package com.example.crud3.models.dtos.userDtos;

import com.example.crud3.models.dtos.profileDtos.ProfileIn;
import com.example.crud3.models.entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class UserInTest {

    @Test
    void convertToEntity() {

    }

    @Test
    void convertToEntityWhenEntityIsNull() {

        final UserIn userIn = new UserIn(anyString(), anyString(), anyString(), new ProfileIn());


        UserEntity userEntity = userIn.convertToEntity(null);

        Assertions.assertNotNull(userEntity);
        Assertions.assertNull(userEntity.getId());
    }
}