package com.example.crud3.repositories;

import com.example.crud3.models.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    @Transactional
    @Modifying
    ProfileEntity updateById(Long id, ProfileEntity profile);
}
