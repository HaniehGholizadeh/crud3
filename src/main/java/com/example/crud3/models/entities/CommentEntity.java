package com.example.crud3.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "comments")
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}
