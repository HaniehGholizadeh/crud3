package com.example.crud3.models.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "post_entity")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String title;
    @CreationTimestamp
    private LocalDateTime date;

    @ManyToMany
    @JoinTable(name = "post_tags",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<TagEntity> tags = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<CommentEntity> comments;

    public void addTag(TagEntity tag) {
        this.getTags().add(tag);
//        tags.add(tag);
    }
}
