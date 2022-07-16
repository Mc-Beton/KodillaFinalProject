package com.imdbmovie.kodillafinalproject.post.service;

import com.imdbmovie.kodillafinalproject.exceptions.PostNotFoundException;
import com.imdbmovie.kodillafinalproject.exceptions.UserNotFoundException;
import com.imdbmovie.kodillafinalproject.post.domain.Post;
import com.imdbmovie.kodillafinalproject.post.domain.PostDto;
import com.imdbmovie.kodillafinalproject.post.mapper.PostMapper;
import com.imdbmovie.kodillafinalproject.user.domain.User;
import com.imdbmovie.kodillafinalproject.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTestSuite {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserService userService;

    @Test
    void addNewPostTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        Post post = new Post("tt123456", user, "Content");

        //When
        postService.createNewPostToMovieByUser(post);

        //Then
        assertEquals(1, postService.getAllPosts().size());

        //Clean up
        postService.deletePost(post.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    void getPostByIdTest() throws PostNotFoundException {
        //given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);

        //When
        Post post = new Post("tt123456", user, "Content");
        postService.createNewPostToMovieByUser(post);
        Long postId = post.getId();

        //Then
        assertEquals(post.getContent(), postService.getPostById(postId).getContent());

        //Clean up
        postService.deletePost(postId);
        userService.deleteUser(user.getId());
    }

    @Test
    void getPostsForMovieTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        Post post = new Post("tt123456", user, "Content");
        Post post2 = new Post("tt123456", user, "Content2");
        Post post3 = new Post("tt123456", user, "Content3");

        //When
        postService.createNewPostToMovieByUser(post);
        postService.createNewPostToMovieByUser(post2);
        postService.createNewPostToMovieByUser(post3);

        //Then
        assertEquals(3, postService.getPostsForMovie("tt123456").size());

        //Clean up
        postService.deletePost(post.getId());
        postService.deletePost(post2.getId());
        postService.deletePost(post3.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    void shouldMapToPostDtoTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        Post post = new Post("tt123456", user, "content");

        //When
        PostDto postDto = postMapper.mapToPostDto(post);

        //Then
        assertEquals(post.getContent(), postDto.getContent());
        assertEquals(post.getUser().getUsername(), postDto.getUsername());
    }

    @Test
    void shouldMapToPostTest() throws UserNotFoundException {
        //Given
        PostDto postDto = new PostDto("tt123456", "username", "content");
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);

        //When
        Post post = postMapper.mapToPost(postDto);

        //Then
        assertEquals(post.getUser().getUsername(), postDto.getUsername());
        assertEquals(post.getContent(), postDto.getContent());

        //clean up
        userService.deleteUser(user.getId());
    }

    @Test
    void shouldMapToDtoListTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        List<Post> post = List.of(new Post("tt123456", user, "content"));

        //When
        List<PostDto> postDtos = postMapper.mapToPostDtoList(post);

        //Then
        assertEquals(postDtos.size(), 1);
    }

    @Test
    void shouldThrowPostNotFoundExceptionTest() throws PostNotFoundException {
        //Given
        //When & Then
        PostNotFoundException thrown = Assertions.assertThrows(PostNotFoundException.class, () ->{
            postService.getPostById(1L);
        });
    }
}