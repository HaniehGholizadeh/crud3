package com.example.crud3.models.dtos.profileDtos;

import com.example.crud3.models.entities.ProfileEntity;
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
public class ProfileEditIn {
    private String city;
    private String country;
    @Pattern(regexp = "[0-9]{10}", message = "code posti bayad 10 raghami bashad.")
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
}
