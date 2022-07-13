package com.imdbmovie.kodillafinalproject.rating.mapper;

import com.imdbmovie.kodillafinalproject.rating.domain.AverageRating;
import com.imdbmovie.kodillafinalproject.rating.domain.dto.AverageRatingDto;
import org.springframework.stereotype.Service;

@Service
public class AverageRatingMapper {

    public AverageRatingDto mapToAvgRatingDto(AverageRating rating) {
        return new AverageRatingDto(
                rating.getMovieId(),
                rating.getAverageRate()
        );
    }
}
