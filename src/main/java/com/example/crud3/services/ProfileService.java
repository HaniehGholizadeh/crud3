package com.example.crud3.services;

import com.example.crud3.models.dtos.profileDtos.ProfileEditIn;
import com.example.crud3.models.dtos.profileDtos.ProfileIn;
import com.example.crud3.models.dtos.profileDtos.ProfileOut;
import com.example.crud3.models.entities.ProfileEntity;
import com.example.crud3.repositories.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class ProfileService {
    final ProfileRepository profileRepository;

    public ProfileEntity createProfile(@Valid ProfileIn model) {
        ProfileEntity profileEntity = model.convertToEntity(new ProfileEntity());
        return profileEntity;
    }

    public List<ProfileOut> getAllProfiles() {
        return profileRepository.findAll().stream().map(ProfileOut::new).collect(Collectors.toList());
    }

    public ProfileEntity updateProfile(ProfileEntity profile, @Valid ProfileEditIn model) {
        ProfileEntity profileEntity = model.convertToEntity(profile);
        return profileRepository.save(profileEntity);
    }
}
