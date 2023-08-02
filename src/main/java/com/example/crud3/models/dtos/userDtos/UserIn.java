package com.example.crud3.models.dtos.userDtos;

import com.example.crud3.models.entities.ProfileEntity;
import com.example.crud3.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserIn {
    @Size(min = 6, message = "Username must have at least 6 characters")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*])(?=\\S+$).{8,20}$", message = "Passwords must have at least 8 characters and contain at least one uppercase letter, lowercase letter, number, and symbol.")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9_+&*-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email")
    private String email;

    private Long profileId;
    private String city;
    private String country;
    @Pattern(regexp = "[0-9]{10}", message = "code posti bayad 10 raghami bashad.")
    private String postCode;


    public UserEntity convertToEntity(UserEntity entity) {
        if (entity == null) {
            entity = new UserEntity();
        }
        entity.setEmail(email);
        entity.setUsername(username);
        entity.setPassword(password);
        return entity;
    }

    public ProfileEntity convertToEntity(ProfileEntity profile) {
        if (profile == null) {
            profile = new ProfileEntity();
        }
        profile.setCity(city);
        profile.setCountry(country);
        profile.setPostCode(postCode);
        return profile;
    }
}
