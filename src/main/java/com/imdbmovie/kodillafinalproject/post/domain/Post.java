package com.imdbmovie.kodillafinalproject.post.domain;

import com.imdbmovie.kodillafinalproject.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOVIE_ID")
    private String movieId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "CONTENT")
    private String content;

    public Post(String movieId, User user, String content) {
        this.movieId = movieId;
        this.user = user;
        this.content = content;
    }
}
