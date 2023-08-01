package com.example.crud3.services;

import com.example.crud3.models.dtos.ProfileIn;
import com.example.crud3.models.dtos.ProfileOut;
import com.example.crud3.models.entities.ProfileEntity;
import com.example.crud3.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileOut create(ProfileIn model) {
        ProfileEntity profileEntity = model.convertToEntity(new ProfileEntity());
        ProfileEntity profile = profileRepository.save(profileEntity);
        return new ProfileOut(profile);
    }
}
