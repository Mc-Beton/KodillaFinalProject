package com.imdbmovie.kodillafinalproject.rating.service;

import com.imdbmovie.kodillafinalproject.rating.Repository.AverageRepository;
import com.imdbmovie.kodillafinalproject.rating.Repository.RatingRepository;
import com.imdbmovie.kodillafinalproject.rating.domain.AverageRating;
import com.imdbmovie.kodillafinalproject.rating.domain.Rating;
import com.imdbmovie.kodillafinalproject.exceptions.RatingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final AverageRepository averageRepository;

    public Rating saveNewRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> getRatingsByMovie(String id) {
        Optional<List<Rating>> movieRating = Optional.of(ratingRepository.findAllByMovieId(id).get());
        return movieRating.orElseGet(ArrayList::new);
    }

    public Rating getRatingById(Long id) throws RatingNotFoundException {
        Optional<Rating> rating = Optional.of(ratingRepository.findById(id).get());
        return rating.get();
    }

    public Rating getRatingByUserAndMovie(String movieId, String username) throws RatingNotFoundException {
        Optional<Rating> rating = Optional.of(ratingRepository.findByMovieIdAndAndUser_Username(movieId, username).get());
        return rating.get();
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    public double calculateAvgRating(String movieId) {
        List<Rating> ratings = getRatingsByMovie(movieId);
        return ratings.stream()
                .mapToDouble(Rating::getMovieRate)
                .average().getAsDouble();
    }

    public AverageRating getAvgRatingOfMovie(String movieId) {
        if (averageRepository.findByMovieId(movieId).isPresent())
            return averageRepository.findByMovieId(movieId).get();
        else
            return new AverageRating(movieId, 0.0);
    }

    public AverageRating saveNewCalculation(AverageRating averageRating) {
        return averageRepository.save(averageRating);
    }

    public void clearAvgRatings() {
        averageRepository.deleteAll();
    }
}
