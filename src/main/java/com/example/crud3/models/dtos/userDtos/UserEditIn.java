package com.example.crud3.models.dtos.userDtos;


import com.example.crud3.models.entities.ProfileEntity;
import com.example.crud3.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEditIn {
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Passwords must have at least 8 characters and contain at least one uppercase letter, lowercase letter, number, and symbol.")
    private String password;

    @Nullable
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9_+&*-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email")
    private String email;

    @Nullable
    private String city;

    @Nullable
    private String country;

    @Pattern(regexp = "[0-9]{10}", message = "code posti bayad 10 raghami bashad.")
    @Nullable
    private String postCode;

    public ProfileEntity convertToEntity(ProfileEntity entity) {
        if (entity == null) {
            entity = new ProfileEntity();
        }
        entity.setCity(Optional.ofNullable(city).orElse(entity.getCity()));
        entity.setCountry(Optional.ofNullable(country).orElse(entity.getCountry()));
        entity.setPostCode(Optional.ofNullable(postCode).orElse(entity.getPostCode()));
        return entity;
    }

    public UserEntity convertToEntity(UserEntity entity) {
        if (entity == null) {
            entity = new UserEntity();
        }
        entity.setEmail(Optional.ofNullable(email).orElse(entity.getEmail()));
        entity.setPassword(Optional.ofNullable(password).orElse(entity.getPassword()));
        return entity;
    }
}
