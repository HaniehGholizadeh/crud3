package com.example.crud3.models.dtos.profileDtos;

import com.example.crud3.models.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileOut {
    private Long id;
    private String city;
    private String country;
    private String postCode;

    public ProfileOut(ProfileEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.city = entity.getCity();
            this.country = entity.getCountry();
            this.postCode = entity.getPostCode();
        }
    }
}
