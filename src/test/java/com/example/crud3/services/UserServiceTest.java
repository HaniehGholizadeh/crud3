package com.example.crud3.services;
import com.example.crud3.models.dtos.profileDtos.ProfileEditIn;
import com.example.crud3.models.dtos.profileDtos.ProfileIn;
import com.example.crud3.models.dtos.userDtos.UserEditIn;
import com.example.crud3.models.dtos.userDtos.UserIn;
import com.example.crud3.models.dtos.userDtos.UserOut;
import com.example.crud3.models.entities.ProfileEntity;
import com.example.crud3.models.entities.UserEntity;
import com.example.crud3.repositories.ProfileRepository;
import com.example.crud3.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Spy
    @InjectMocks
    UserService userService;

    @Mock
    ProfileService profileService;

    @Mock
    UserRepository userRepository;

    @Mock
    ProfileRepository profileRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getById() {
    }

    @Test
    public void createUser_success() {

        doReturn(profileEntity(1)).when(profileService).createProfile(any(ProfileIn.class));
        doReturn(userEntity(1)).when(userRepository).save(any(UserEntity.class));

        UserIn userIn = userIn(1);

        UserOut userOut = userService.createUser(userIn);

        assertAll(
                () -> assertEquals(userIn.getProfileIn().getCity(), userOut.getProfileOut().getCity()),
                () -> assertEquals(userOut.getEmail(), userIn.getEmail()),
                () -> assertEquals(userOut.getEmail(), userIn.getEmail()),
                () -> assertEquals(userOut.getUsername(), userIn.getUsername()));
    }


    @Test
    public void getAllUsers_success() {
        List<UserEntity> entities = this.userEntities(5);
        doReturn(entities).when(userRepository).findAll();

        List<UserOut> outs = userService.getAllUsers();

        for (int j = 0; j < entities.size(); j++) {
            int i = j;
            assertAll(
                    () -> assertEquals(entities.get(i).getUsername(), outs.get(i).getUsername()),
                    () -> assertEquals(entities.get(i).getId(), outs.get(i).getId()),
                    () -> assertEquals(entities.get(i).getEmail(), outs.get(i).getEmail()),
                    () -> assertEquals(entities.get(i).getProfile().getCity(), outs.get(i).getProfileOut().getCity()),
                    () -> assertEquals(entities.get(i).getProfile().getPostCode(), outs.get(i).getProfileOut().getPostCode()));
        }
    }

    @Test
    public void deleteById_success() {

    }

    @Test
    public void updateUser_success() {

        UserEditIn userEditIn = userEditIn(2);
        UserEntity userEntity = userEntity(1);

        when(profileRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(profileService.updateProfile(any(ProfileEntity.class), any(ProfileEditIn.class))).thenReturn(profileEntity(2));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserOut userOut = userService.updateUser(1L, userEditIn);

        assertAll(
                () -> assertEquals(userOut.getProfileOut().getCity(), userEditIn.getProfileEditIn().getCity()),
                () -> assertEquals(userOut.getProfileOut().getPostCode(), userEditIn.getProfileEditIn().getPostCode()),
                () -> assertEquals(userOut.getEmail(), userEditIn.getEmail()));
    }

    private UserIn userIn(int value) {
        UserIn userIn = new UserIn();
        userIn.setUsername(value + "userName");
        userIn.setEmail(value + "@gmail.com");
        userIn.setPassword("123456777");
        userIn.setProfileIn(this.profileIn(value));
        return userIn;
    }


    private ProfileIn profileIn(int value) {
        ProfileIn profileIn = new ProfileIn();
        profileIn.setCity(value + "Tehran");
        profileIn.setCountry(value + "Iran");
        profileIn.setPostCode(value + "0123456789");
        return profileIn;
    }

    private UserEditIn userEditIn(int value) {
        UserEditIn userEditIn = new UserEditIn();
        userEditIn.setEmail(value + "@gmail.com");
        userEditIn.setPassword(value + "123456777");
        userEditIn.setProfileEditIn(this.profileEditIn(value));
        return userEditIn;
    }

    private ProfileEditIn profileEditIn(int value) {
        ProfileEditIn profileEditIn = new ProfileEditIn();
        profileEditIn.setCity(value + "Tehran");
        profileEditIn.setCountry(value + "Iran");
        profileEditIn.setPostCode(value + "0123456789");
        return profileEditIn;
    }

    private UserEntity userEntity(int value) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(value + "userName");
        userEntity.setEmail(value + "@gmail.com");
        userEntity.setPassword("123456777");
        userEntity.setProfile(this.profileEntity(value));
        return userEntity;
    }

    private ProfileEntity profileEntity(int value) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setCity(value + "Tehran");
        profileEntity.setCountry(value + "Iran");
        profileEntity.setPostCode(value + "0123456789");
        return profileEntity;
    }

    private List<UserEntity> userEntities(int count) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            userEntities.add(this.userEntity(i));
        }
        return userEntities;
    }

    private void returnVoid() {
        return;
    }
}