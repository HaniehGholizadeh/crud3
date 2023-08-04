package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.profileDtos.ProfileEditIn;
import com.example.crud3.models.dtos.profileDtos.ProfileIn;
import com.example.crud3.models.dtos.profileDtos.ProfileOut;
import com.example.crud3.models.entities.ProfileEntity;
import com.example.crud3.repositories.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public ProfileOut update(Long id, ProfileEditIn model) {
        ProfileEntity profile = profileRepository.findById(id).orElseThrow(() -> new CustomException("Profile not found", 1002, HttpStatus.NOT_FOUND));
        model.convertToEntity(new ProfileEntity());
        ProfileEntity updatedProfile = profileRepository.save(profile);
        return new ProfileOut(updatedProfile);
    }

    public List<ProfileOut> getAllProfiles() {
        return profileRepository.findAll().stream().map(ProfileOut::new).collect(Collectors.toList());
    }
}
