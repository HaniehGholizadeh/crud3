package com.example.crud3.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "users")
@Getter
@Setter
public class UserEntity {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profile_id")
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


