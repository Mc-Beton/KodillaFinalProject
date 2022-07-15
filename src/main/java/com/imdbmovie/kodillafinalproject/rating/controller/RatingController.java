package com.imdbmovie.kodillafinalproject.rating.controller;

import com.imdbmovie.kodillafinalproject.rating.domain.Rating;
import com.imdbmovie.kodillafinalproject.rating.domain.dto.RatingDto;
import com.imdbmovie.kodillafinalproject.exceptions.RatingNotFoundException;
import com.imdbmovie.kodillafinalproject.rating.mapper.RatingMapper;
import com.imdbmovie.kodillafinalproject.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    @GetMapping("/all")
    public ResponseEntity<List<RatingDto>> getAllRatings() {
        List<Rating> ratingList = ratingService.getAllRatings();
        return ResponseEntity.ok(ratingMapper.mapToDtoList(ratingList));
    }

    @GetMapping("/{username}/movie/{movieId}")
    public ResponseEntity<RatingDto> getUserRatingForMovie(@PathVariable String username, @PathVariable String movieId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingMapper.mapToRatingDto(ratingService.getRatingByUserAndMovie(movieId, username)));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<RatingDto>> getMovieRatings(@PathVariable String movieId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingMapper.mapToDtoList(ratingService.getRatingsByMovie(movieId)));
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<RatingDto> getRatingById(@PathVariable Long ratingId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingMapper.mapToRatingDto(ratingService.getRatingById(ratingId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNewRating(@RequestBody RatingDto ratingDto) {
        Rating rating = ratingMapper.mapToRating(ratingDto);
        ratingService.saveNewRating(rating);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.ok().build();
    }
}
