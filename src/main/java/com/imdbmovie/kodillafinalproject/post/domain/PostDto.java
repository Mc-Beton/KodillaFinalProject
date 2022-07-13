package com.imdbmovie.kodillafinalproject.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostDto {

    private Long id;
    private String movieId;
    private String username;
    private String content;

    public PostDto(String movieId, String  username, String content) {
        this.movieId = movieId;
        this.username = username;
        this.content = content;
    }
}
