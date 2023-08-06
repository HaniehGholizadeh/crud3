package com.example.crud3.repositories;

import com.example.crud3.models.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    @Query(value = "select * from tags t join posts_tags pt on pt.t_id=t.id where pt.p_id=:postId", nativeQuery = true)
    Set<TagEntity> findTagsByPostId(Long postId);

    @Modifying
    @Transactional
    @Query(value = "insert into posts_tags (p_id, t_id) values (:postId, :tagId)", nativeQuery = true)
    void addTagToPost(@Param("postId") Long postId, @Param("tagId") Long tagId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO posts_tags (p_id, t_id)  SELECT :postId, t.id FROM tags t where t.id in (:tagIds)")
    void addTagsToPost(@Param("postId") Long postId, @Param("tagIds") Set<Long> tagIds);


}
