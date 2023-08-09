package com.example.crud3.services;

import com.example.crud3.repositories.PostRepository;
import com.example.crud3.repositories.TagRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Spy
    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;
    @Mock
    TagRepository tagRepository;
    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void create() {
    }

    @Test
    public void getTags() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void getAllPosts() {
    }

    @Test
    public void getPost() {
    }
}