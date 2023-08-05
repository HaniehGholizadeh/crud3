package com.example.crud3.models.dtos.userDtos;


import com.example.crud3.models.dtos.profileDtos.ProfileEditIn;
import com.example.crud3.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Optional;

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
    private String city;
    private String country;
    @Pattern(regexp = "[0-9]{10}", message = "code posti bayad 10 raghami bashad.")
    private String postCode;

    public UserEntity convertToEntity(UserEntity entity) {
        if (entity == null) {
            entity = new UserEntity();
        }
        entity.setEmail(Optional.ofNullable(email).orElse(entity.getEmail()));
        entity.setEmail(email != null ? email : entity.getEmail());
        entity.setPassword(Optional.ofNullable(password).orElse(entity.getPassword()));
        entity.setProfile(new ProfileEditIn(city, country, postCode).convertToEntity(entity.getProfile()));
        return entity;
    }
}
