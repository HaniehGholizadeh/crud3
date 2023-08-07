package com.example.crud3.models.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity(name = "profiles")
@Getter
@Setter
public class ProfileEntity {
    @Id
    private Long id;
    private String city;
    private String country;
    private String postCode;

    @OneToOne
    @MapsId
    private UserEntity user;

}
