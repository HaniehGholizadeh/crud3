package com.example.crud3.commandlinerunners;

import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.ProfileEntity;
import com.example.crud3.models.entities.TagEntity;
import com.example.crud3.models.entities.UserEntity;
import com.example.crud3.repositories.PostRepository;
import com.example.crud3.repositories.ProfileRepository;
import com.example.crud3.repositories.TagRepository;
import com.example.crud3.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CreateUserRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    private final TagRepository tagRepository;

    private final PostRepository postRepository;

    public CreateUserRunner(UserRepository userRepository, ProfileRepository profileRepository, TagRepository tagRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity user1 = new UserEntity();
        user1.setUsername("test");
        user1.setEmail("test@gmail.com");
        user1.setPassword("123");

        UserEntity user2 = new UserEntity();
        user2.setUsername("test2");
        user2.setEmail("test2@gmail.com");
        user2.setPassword("123");

        UserEntity user3 = new UserEntity();
        user3.setUsername("test3");
        user3.setEmail("test3@gmail.com");
        user3.setPassword("123");

        ProfileEntity profile1 = new ProfileEntity();
        profile1.setCity("tehran");
        profile1.setCountry("iran");
        profile1.setPostCode("0123456789");

        ProfileEntity profile2 = new ProfileEntity();
        profile2.setCity("london");
        profile2.setCountry("england");
        profile2.setPostCode("0123456789");

        ProfileEntity profile3 = new ProfileEntity();
        profile3.setCity("qom");
        profile3.setCountry("iran");
        profile3.setPostCode("0123456789");

        profileRepository.save(profile1);
        profileRepository.save(profile2);
        profileRepository.save(profile3);

        user1.setProfile(profile1);
        user2.setProfile(profile2);
        user3.setProfile(profile3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);


        TagEntity tag1 = new TagEntity();
        tag1.setName("lavazem khanegi");

        TagEntity tag2 = new TagEntity();
        tag2.setName("lavazem behdashti");

        TagEntity tag3 = new TagEntity();
        tag3.setName("lavazem varzeshi");

        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag3);

        PostEntity post = new PostEntity();
        postRepository.save(post);

    }
}
