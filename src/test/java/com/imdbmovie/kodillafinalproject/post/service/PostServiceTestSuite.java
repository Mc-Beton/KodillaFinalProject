package com.imdbmovie.kodillafinalproject.post.service;

import com.imdbmovie.kodillafinalproject.post.domain.Post;
import com.imdbmovie.kodillafinalproject.post.mapper.PostMapper;
import com.imdbmovie.kodillafinalproject.user.domain.User;
import com.imdbmovie.kodillafinalproject.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}