package com.example.crud3.services;

import com.example.crud3.models.dtos.profileDtos.ProfileIn;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

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

        doReturn(this.profileEntity()).when(profileService).createProfile(any(ProfileIn.class));
        doReturn(this.userEntity(1)).when(userRepository).save(any(UserEntity.class));

        UserIn userIn = this.userIn("test");

        UserOut userOut = userService.createUser(userIn);

//        assertAll((() -> assertNotNull(userOut.getId())),
//                () -> assertNotNull(userOut.getProfileOut()),
//                () -> assertEquals(userIn.getProfileIn().getCity(), userOut.getProfileOut().getCity()),
//                () -> assertEquals(userOut.getEmail(),userIn.getEmail()),
//                () -> assertEquals(userOut.getEmail(),userIn.getEmail()),
//                () -> assertEquals(userOut.getUsername(),userIn.getUsername()));

        assertAll(
                () -> assertEquals(userIn.getProfileIn().getCity(), userOut.getProfileOut().getCity()),
                () -> assertEquals(userOut.getEmail(), userIn.getEmail()),
                () -> assertEquals(userOut.getEmail(), userIn.getEmail()),
                () -> assertEquals(userOut.getUsername(), userIn.getUsername()));
    }


    private ProfileIn profileIn() {
        ProfileIn profileIn = new ProfileIn();
        profileIn.setCity("tehran");
        profileIn.setCountry("iran");
        profileIn.setPostCode("0123456789");
        return profileIn;
    }


    @Test
    public void getAllUsers() {
        List<UserEntity> userEntities = this.userEntities(5);
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void updateUser() {
    }

    private UserIn userIn(String value) {
        UserIn userIn = new UserIn();
        userIn.setUsername(value + "userName");
        userIn.setEmail(value + "@gmail.com");
        userIn.setPassword("123456777");
        userIn.setProfileIn(this.profileIn());
        return userIn;
    }

    private UserEntity userEntity(int value) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(value + "userName");
        userEntity.setEmail(value + "@gmail.com");
        userEntity.setPassword("123456777");
        userEntity.setProfile(this.profileEntity());
        return userEntity;
    }

    private ProfileEntity profileEntity() {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(1L);
        profileEntity.setCity("tehran");
        profileEntity.setCountry("iran");
        profileEntity.setPostCode("0123456789");
        return profileEntity;
    }

    private List<UserEntity> userEntities(int cont) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (int i = 0; i < cont; i++) {
            userEntities.add(this.userEntity(i));
        }
        return userEntities;
    }
}