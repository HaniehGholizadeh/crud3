package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.profileDtos.ProfileIn;
import com.example.crud3.models.dtos.profileDtos.ProfileOut;
import com.example.crud3.models.dtos.userDtos.UserEditIn;
import com.example.crud3.models.dtos.userDtos.UserIn;
import com.example.crud3.models.dtos.userDtos.UserOut;
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
    private final ProfileRepository profileRepository;

    public UserService(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public UserOut getById(Long id) {
        check(id);
        return new UserOut(userRepository.findById(id).get());
    }

    public UserOut createUser(UserIn model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        model.setPassword((hasPassword(model.getPassword())));
        UserEntity userEntity = model.convertToEntity(new UserEntity());
        this.createProfile(model.getProfileIn());
//        userEntity.setProfile(profileEntity);
        return new UserOut(userRepository.save(userEntity));
    }

    public ProfileOut createProfile(ProfileIn model) {
        ProfileEntity profileEntity = model.convertToEntity(new ProfileEntity());
        ProfileEntity profile = profileRepository.save(profileEntity);
        return new ProfileOut(profile);
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

    public List<UserOut> getAllUsers() throws CustomException {
        return userRepository.findAll().stream().map(UserOut::new).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        check(id);
        userRepository.deleteById(id);
    }


    public UserOut updateUser(Long id, UserEditIn model) throws NoSuchAlgorithmException, InvalidKeySpecException {
        check(id);
        if (model.getPassword() != null) {
            model.setPassword(hasPassword(model.getPassword()));
        }
        UserEntity userEntity = model.convertToEntity(new UserEntity());
        return new UserOut(userRepository.save(userEntity));
    }

    private void check(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new CustomException("Usr not found", 100, HttpStatus.NOT_FOUND);
        }
    }
}