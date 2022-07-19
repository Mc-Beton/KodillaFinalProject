package com.imdbmovie.kodillafinalproject.rating.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AverageRatingDto {

    private String movieId;
    private double averageRating;
}
