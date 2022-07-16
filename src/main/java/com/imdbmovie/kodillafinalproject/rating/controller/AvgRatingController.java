package com.imdbmovie.kodillafinalproject.rating.controller;

import com.imdbmovie.kodillafinalproject.rating.domain.dto.AverageRatingDto;
import com.imdbmovie.kodillafinalproject.rating.mapper.AverageRatingMapper;
import com.imdbmovie.kodillafinalproject.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avgRating")
@RequiredArgsConstructor
public class AvgRatingController {

    private final RatingService ratingService;
    private final AverageRatingMapper mapper;

    @GetMapping("/{movieId}")
    public ResponseEntity<AverageRatingDto> getAvgRatingOfMovie(@PathVariable String movieId) {
        return ResponseEntity.ok(mapper.mapToAvgRatingDto(ratingService.getAvgRatingOfMovie(movieId)));
    }
}
