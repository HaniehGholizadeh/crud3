package com.example.crud3.commandlinerunners;

import com.example.crud3.models.entities.UserEntity;
import com.example.crud3.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CreateUserRunner implements CommandLineRunner {
    private final UserRepository userRepository;

    public CreateUserRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
