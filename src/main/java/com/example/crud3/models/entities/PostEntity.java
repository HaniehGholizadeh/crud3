package com.example.crud3.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @CreationTimestamp()
    private LocalDateTime CreationDate;
    private LocalDateTime publishDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "posts_tags",
            joinColumns = {@JoinColumn(name = "p_id")},
            inverseJoinColumns = {@JoinColumn(name = "t_id")})
    private Set<TagEntity> tags = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<CommentEntity> comments = new HashSet<>();

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + CreationDate +
                ", comments=" + comments +
                '}';
    }
}
