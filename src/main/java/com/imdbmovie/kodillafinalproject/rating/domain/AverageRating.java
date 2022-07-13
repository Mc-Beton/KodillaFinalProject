package com.imdbmovie.kodillafinalproject.rating.domain;

import com.imdbmovie.kodillafinalproject.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AverageRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOVIE_ID")
    private String movieId;

    @Column(name = "AVERAGE_RATING")
    private double averageRate;

    public AverageRating(String movieId, double averageRate) {
        this.movieId = movieId;
        this.averageRate = averageRate;
    }
}
