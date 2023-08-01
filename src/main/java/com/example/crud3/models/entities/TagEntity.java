package com.example.crud3.models.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<PostEntity> posts;

}
