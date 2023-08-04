package com.example.crud3.models.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "profiles")
@Getter
@Setter
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private String postCode;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile")
    private UserEntity user;

}
