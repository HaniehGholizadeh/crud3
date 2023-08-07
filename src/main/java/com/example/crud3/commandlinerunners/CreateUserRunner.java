package com.example.crud3.commandlinerunners;

import com.example.crud3.models.entities.*;
import com.example.crud3.repositories.CommentRepository;
import com.example.crud3.repositories.PostRepository;
import com.example.crud3.repositories.TagRepository;
import com.example.crud3.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CreateUserRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Override
    public void run(String... args) throws Exception {
        UserEntity user1 = new UserEntity();
        user1.setUsername("test");
        user1.setEmail("test@gmail.com");
        user1.setPassword("123CD");

        UserEntity user2 = new UserEntity();
        user2.setUsername("test2");
        user2.setEmail("test2@gmail.com");
        user2.setPassword("123BC");

        UserEntity user3 = new UserEntity();
        user3.setUsername("test3");
        user3.setEmail("test3@gmail.com");
        user3.setPassword("123AB");

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

        user1.setProfile(profile1);
        user2.setProfile(profile2);
        user3.setProfile(profile3);

        profile1.setUser(user1);
        profile2.setUser(user2);
        profile3.setUser(user3);

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

        CommentEntity com1 = new CommentEntity();
        com1.setText("comment 1 text is here");

        CommentEntity com2 = new CommentEntity();
        com2.setText("comment 2 text is here");

        CommentEntity com3 = new CommentEntity();
        com3.setText("comment 3 text is here");

        CommentEntity com4 = new CommentEntity();
        com4.setText("comment 4 text is here");

        PostEntity post = new PostEntity();
        post.setTitle("the test post");

        post.getComments().add(com1);
        post.getComments().add(com2);
        post.getComments().add(com3);
        post.getComments().add(com4);

        com1.setPost(post);
        com2.setPost(post);
        com3.setPost(post);
        com4.setPost(post);

        postRepository.save(post);

        commentRepository.save(com1);
        commentRepository.save(com2);
        commentRepository.save(com3);
        commentRepository.save(com4);

    }
}
