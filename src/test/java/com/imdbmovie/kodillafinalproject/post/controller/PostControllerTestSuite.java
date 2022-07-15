package com.imdbmovie.kodillafinalproject.post.controller;

import com.google.gson.Gson;
import com.imdbmovie.kodillafinalproject.imdbMovie.config.CoreConfiguration;
import com.imdbmovie.kodillafinalproject.post.domain.Post;
import com.imdbmovie.kodillafinalproject.post.domain.PostDto;
import com.imdbmovie.kodillafinalproject.post.mapper.PostMapper;
import com.imdbmovie.kodillafinalproject.post.service.PostService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(PostController.class)
@Import(CoreConfiguration.class)
class PostControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private PostMapper postMapper;

    @Test
    void getAllPostsTest() throws Exception {
        //Given
        List<PostDto> postDtoList = List.of(new PostDto("tt123456", "username", "content"));
        List<Post> postList = List.of(new Post());

        when(postMapper.mapToPostDtoList(postList)).thenReturn(postDtoList);
        when(postService.getAllPosts()).thenReturn(postList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/posts/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("content")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("username")));
    }

    @Test
    void getPostForMovieTest() throws Exception {
        //Given
        List<PostDto> postDtoList = List.of(new PostDto("tt123456", "username", "content"));
        List<Post> postList = List.of(new Post());

        when(postMapper.mapToPostDtoList(postList)).thenReturn(postDtoList);
        when(postService.getPostsForMovie("tt123456")).thenReturn(postList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/posts/tt123456")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("content")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("username")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId", Matchers.is("tt123456")));
    }

    @Test
    void createNewPostTest() throws Exception {
        //Given
        Post post = new Post();
        PostDto postDto = new PostDto("tt123456", "username", "content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(postDto);

        when(postService.createNewPostToMovieByUser(post)).thenReturn(post);
        when(postMapper.mapToPost(postDto)).thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void deletePostTest() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/posts/delete/1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}