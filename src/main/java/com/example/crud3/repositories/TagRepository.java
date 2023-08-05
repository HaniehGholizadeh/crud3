package com.example.crud3.repositories;

import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
