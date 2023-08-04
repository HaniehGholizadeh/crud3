package com.example.crud3.models.dtos.userDtos;


import com.example.crud3.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOut {

    private Long id;
    private String username;
    private String email;
    private String city;
    private String country;
    private String postCode;

    public UserOut(UserEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.username = entity.getUsername();
            this.email = entity.getEmail();
            this.city = entity.getProfile().getCity();
            this.country = entity.getProfile().getCountry();
            this.postCode = entity.getProfile().getPostCode();
        }
    }
}
