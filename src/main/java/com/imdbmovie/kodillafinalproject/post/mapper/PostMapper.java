package com.imdbmovie.kodillafinalproject.post.mapper;

import com.imdbmovie.kodillafinalproject.exceptions.UserNotFoundException;
import com.imdbmovie.kodillafinalproject.post.domain.Post;
import com.imdbmovie.kodillafinalproject.post.domain.PostDto;
import com.imdbmovie.kodillafinalproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostMapper {

    private final UserService userService;

    public PostMapper(UserService userService) {
        this.userService = userService;
    }

    public Post mapToPost(PostDto postDto) throws UserNotFoundException {
        return new Post(
                postDto.getMovieId(),
                userService.getUserByUsername(postDto.getUsername()),
                postDto.getContent()
        );
    }

    public PostDto mapToPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getMovieId(),
                post.getUser().getUsername(),
                post.getContent()
        );
    }

    public List<PostDto> mapToPostDtoList(List<Post> postList) {
        return postList.stream()
                .map(this::mapToPostDto)
                .collect(Collectors.toList());
    }
}
