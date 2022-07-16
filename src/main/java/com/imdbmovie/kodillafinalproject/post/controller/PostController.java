package com.imdbmovie.kodillafinalproject.post.controller;

import com.imdbmovie.kodillafinalproject.exceptions.PostNotFoundException;
import com.imdbmovie.kodillafinalproject.exceptions.UserNotFoundException;
import com.imdbmovie.kodillafinalproject.post.domain.Post;
import com.imdbmovie.kodillafinalproject.post.domain.PostDto;
import com.imdbmovie.kodillafinalproject.post.mapper.PostMapper;
import com.imdbmovie.kodillafinalproject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getPostList() {
        List<Post> postList = postService.getAllPosts();
        return ResponseEntity.ok(postMapper.mapToPostDtoList(postList));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<PostDto>> getMoviePostList(@PathVariable String movieId) {
        List<Post> postList = postService.getPostsForMovie(movieId);
        return ResponseEntity.ok(postMapper.mapToPostDtoList(postList));
    }

    @GetMapping("/byId/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) throws PostNotFoundException {
        return ResponseEntity.ok(postMapper.mapToPostDto(postService.getPostById(postId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNewPost(@RequestBody PostDto postDto) throws UserNotFoundException {
        Post post = postMapper.mapToPost(postDto);
        postService.createNewPostToMovieByUser(post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
