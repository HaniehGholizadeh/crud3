package com.example.crud3.services;

import com.example.crud3.exceptionHandler.CustomException;
import com.example.crud3.models.dtos.postDtos.PostIn;
import com.example.crud3.models.dtos.postDtos.PostOut;
import com.example.crud3.models.dtos.tagDtos.TagOut;
import com.example.crud3.models.entities.PostEntity;
import com.example.crud3.models.entities.TagEntity;
import com.example.crud3.repositories.PostRepository;
import com.example.crud3.repositories.TagRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


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
    public void create_success() {
        PostIn postIn = postIn(1);
        doReturn(tagEntities(1)).when(tagRepository).findAllById(ArgumentMatchers.anySet());
        doReturn(postEntity(1)).when(postRepository).save(any(PostEntity.class));
        PostOut postOut = postService.create(postIn);
        assertAll(() -> assertEquals(postOut.getTitle(), postIn.getTitle()), () -> assertEquals(postOut.getPublishDate(), postIn.getPublishDate()), () -> assertEquals(postOut.getTags().stream().map(TagOut::getId).collect(Collectors.toSet()), postIn.getTagIds().stream().collect(Collectors.toSet())));
    }

    @Test
    public void create_CustomException_exception() {
        doReturn(tagEntities(1)).when(tagRepository).findAllById(ArgumentMatchers.anySet());
        doReturn(postEntity(1)).when(postRepository).save(any(PostEntity.class));
        PostIn postIn = postIn(1);
        postIn.setPublishDate(LocalDateTime.of(2020, 10, 1, 15, 0));
        assertThrows(CustomException.class, () -> postService.create(postIn));
    }

    @Test
    public void getTags() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void getAllPosts_success() {
        List<PostEntity> entities = postEntities(3);

        doReturn(entities).when(postRepository).findAll();

        List<PostOut> outs = postService.getAllPosts();

        assertEquals(outs.size(), entities.size());

        for (int j = 0; j < entities.size(); j++) {
            int i = j;
            assertAll(() -> assertEquals(entities.get(i).getTitle(), outs.get(i).getTitle()), () -> assertEquals(entities.get(i).getPublishDate(), outs.get(i).getPublishDate()), () -> assertEquals(entities.get(i).getTags().stream().map(TagEntity::getId).collect(Collectors.toSet()), outs.get(i).getTags().stream().map(TagOut::getId).collect(Collectors.toSet())));
        }

    }

    @Test
    public void getAllPosts_CustomException_exception() {
        List<PostEntity> entities = new ArrayList<>();
        doReturn(entities).when(postRepository).findAll();
        assertThrows(CustomException.class, () -> postService.getAllPosts());
    }

    @Test
    public void getPost() {
    }

    private PostIn postIn(int value) {
        PostIn postIn = new PostIn();
        postIn.setTitle(value + " test post");
        long longValue = value;
        Set<Long> tagIds = new HashSet<>();
        for (int i = 0; i < value + 5; i++) {
            tagIds.add(longValue + i);
        }
        postIn.setTagIds(tagIds);
        postIn.setPublishDate(LocalDateTime.of(2024, 10, 28, 15, 0));
        return postIn;
    }

    private PostEntity postEntity(int value) {
        PostEntity postEntity = new PostEntity();
        long longValue = value;
        postEntity.setTitle(value + " test post");
        Set<TagEntity> tags = new LinkedHashSet<>();
        for (int i = 0; i < value + 5; i++) {
            TagEntity tag = new TagEntity();
            tag.setId(longValue + i);
            tags.add(tag);
        }
        postEntity.setTags(new HashSet<>(tags));
        postEntity.setPublishDate(LocalDateTime.of(2023, 10, 1 + value, 15, 0));
        return postEntity;
    }

    private List<TagEntity> tagEntities(int value) {
        long longValue = value;
        List<TagEntity> tags = new ArrayList<>();
        for (int i = 0; i < value + 5; i++) {
            TagEntity tag = new TagEntity();
            tag.setId(longValue + i);
            tags.add(tag);
        }
        return tags;
    }

    private List<PostEntity> postEntities(int count) {
        List<PostEntity> posts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            PostEntity post = postEntity(i);
            posts.add(post);
        }
        return posts;
    }
}