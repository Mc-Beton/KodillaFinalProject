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
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOVIE_ID")
    private String movieId;

    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;

    @Column(name = "RATE")
    private Integer movieRate;

    public Rating(String movieId, User user, Integer movieRate) {
        this.movieId = movieId;
        this.user = user;
        this.movieRate = movieRate;
    }
}
