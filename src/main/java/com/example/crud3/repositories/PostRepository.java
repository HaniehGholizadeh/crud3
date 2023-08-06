package com.example.crud3.repositories;

import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {


    @Query(value = "select * from post p join posts_tags pt on pt.t_id=p.id where pt.p_id=:tagId", nativeQuery = true)
    Set<PostEntity> findPostsByTagId(Long tagId);
}
