package com.imdbmovie.kodillafinalproject.rating.scheduler;

import com.imdbmovie.kodillafinalproject.rating.domain.AverageRating;
import com.imdbmovie.kodillafinalproject.rating.domain.Rating;
import com.imdbmovie.kodillafinalproject.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpdateMovieRank {

    @Autowired
    private RatingService ratingService;

    @Scheduled(cron = "0 0 * * * *")
    public void updateAllMovieRanks() {
        ratingService.clearAvgRatings();
        List<Rating> ratingsList = ratingService.getAllRatings();
        List<String> ratedMovies = ratingsList.stream()
                .map(Rating::getMovieId)
                .distinct()
                .collect(Collectors.toList());

        ratedMovies
                .forEach(m -> {
                    ratingService.saveNewCalculation(new AverageRating(m, ratingService.calculateAvgRating(m)));
                });
    }
}
