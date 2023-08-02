package com.example.crud3.models.dtos.userDtos;


import com.example.crud3.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEditIn {
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Passwords must have at least 8 characters and contain at least one uppercase letter, lowercase letter, number, and symbol.")
    private String password;
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9_+&*-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email")
    private String email;

    public UserEntity convertToEntity(UserEntity entity) {
        if (entity == null) {
            entity = new UserEntity();
        }
        entity.setEmail(email);
        return entity;
    }
}
