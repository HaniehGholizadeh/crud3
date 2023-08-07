package com.example.crud3.models.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "comments")
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "post")
    private PostEntity post;
}
