package com.imdbmovie.kodillafinalproject.rating.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RatingDto {

    private Long id;
    private String movieId;
    private String username;
    private Integer rating;

    public RatingDto(String movieId, String username, Integer rating) {
        this.movieId = movieId;
        this.username = username;
        this.rating = rating;
    }
}
