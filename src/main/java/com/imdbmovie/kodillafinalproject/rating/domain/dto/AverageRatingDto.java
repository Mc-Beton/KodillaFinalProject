package com.imdbmovie.kodillafinalproject.rating.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AverageRatingDto {

    private Long id;
    private String movieId;
    private double averageRating;

    public AverageRatingDto(String movieId, double averageRating) {
        this.movieId = movieId;
        this.averageRating = averageRating;
    }
}
