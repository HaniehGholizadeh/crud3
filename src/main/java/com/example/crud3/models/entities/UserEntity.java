package com.example.crud3.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "user_entity")
public class UserEntity {
    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private ProfileEntity profile;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;


}


