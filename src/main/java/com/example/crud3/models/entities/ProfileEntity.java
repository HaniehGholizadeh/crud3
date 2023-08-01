package com.example.crud3.models.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "profiles")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private String postCode;

}
