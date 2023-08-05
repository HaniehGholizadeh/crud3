package com.example.crud3.models.dtos.profileDtos;

import com.example.crud3.models.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileIn {
    private String city;
    private String country;
    @Pattern(regexp = "[0-9]{10}", message = "code posti bayad 10 raghami bashad.")
    @NotNull(message = "code posti ra vared konid")
    private String postCode;

    public ProfileEntity convertToEntity(ProfileEntity entity) {
        if (entity == null) {
            entity = new ProfileEntity();
        }
        entity.setCity(city);
        entity.setCountry(country);
        entity.setPostCode(postCode);
        return entity;
    }


}
