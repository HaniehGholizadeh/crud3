package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.UserEditIn;
import com.example.crud3.models.dtos.UserIn;
import com.example.crud3.models.dtos.UserOut;
import com.example.crud3.models.entities.ProfileEntity;
import com.example.crud3.models.entities.UserEntity;
import com.example.crud3.repositories.ProfileRepository;
import com.example.crud3.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    final ProfileRepository profileRepository;

    public UserService(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public UserOut getById(Long id) {
        return new UserOut(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserOut create(UserIn model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserEntity userEntity;
        ProfileEntity profile;
        if (model.getProfileId() == null) {
            profile = model.convertToEntity(new ProfileEntity());
            profileRepository.save(profile);
        } else {
            profile = profileRepository.findById(model.getProfileId()).orElseThrow(() -> new CustomException("profile not found", 1003, HttpStatus.NOT_FOUND));
        }
        userEntity = model.convertToEntity(new UserEntity());
        userEntity.setProfile((profile));
        userEntity.setPassword(hasPassword(model.getPassword()));
        UserEntity newUser = userRepository.save(userEntity);
        return new UserOut(newUser);
    }

    private String hasPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Arrays.toString(hash);
    }

    public List<UserOut> getALl() throws CustomException {

        return userRepository.findAll()
                .stream()
                .map(UserOut::new)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new CustomException("user not found", 1001, HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }


    public UserOut update(Long id, UserEditIn model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", 1002, HttpStatus.NOT_FOUND));
        model.setPassword(hasPassword(model.getPassword()));
        model.convertToEntity(user);
        UserEntity updatedUser = userRepository.save(user);
        return new UserOut(updatedUser);
    }
}