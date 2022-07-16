package com.imdbmovie.kodillafinalproject.rating.mapper;

import com.imdbmovie.kodillafinalproject.exceptions.UserNotFoundException;
import com.imdbmovie.kodillafinalproject.rating.domain.Rating;
import com.imdbmovie.kodillafinalproject.rating.domain.dto.RatingDto;
import com.imdbmovie.kodillafinalproject.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingMapper {

    private UserService userService;

    public RatingMapper(UserService userService) {
        this.userService = userService;
    }

    public Rating mapToRating(RatingDto ratingDto) throws UserNotFoundException {
        return new Rating(
                ratingDto.getMovieId(),
                userService.getUserByUsername(ratingDto.getUsername()),
                ratingDto.getRating()
        );
    }

    public RatingDto mapToRatingDto(Rating rating) {
        return new RatingDto(
                rating.getId(),
                rating.getMovieId(),
                rating.getUser().getUsername(),
                rating.getMovieRate()
        );
    }

    public List<RatingDto> mapToDtoList(List<Rating> ratingList) {
        return ratingList.stream()
                .map(this::mapToRatingDto)
                .collect(Collectors.toList());
    }
}
