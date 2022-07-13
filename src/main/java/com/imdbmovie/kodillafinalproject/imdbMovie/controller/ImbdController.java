package com.imdbmovie.kodillafinalproject.imdbMovie.controller;

import com.imdbmovie.kodillafinalproject.imdbMovie.client.IMBDClient;
import com.imdbmovie.kodillafinalproject.imdbMovie.domain.ImbdMovieDetailsDto;
import com.imdbmovie.kodillafinalproject.imdbMovie.domain.ImdbMovieDto;
import com.imdbmovie.kodillafinalproject.imdbMovie.utellyService.client.UtellyClient;
import com.imdbmovie.kodillafinalproject.imdbMovie.utellyService.domain.LocationWatch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movies/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImbdController {

    private final IMBDClient imbdClient;

    @GetMapping
    public ResponseEntity<List<ImdbMovieDto>> getFirstPage() {
        return ResponseEntity.ok(imbdClient.getMoviesImdb());
    }

    @GetMapping("soon")
    public ResponseEntity<List<ImdbMovieDto>> getSoonPage() {
        return ResponseEntity.ok(imbdClient.getComingSoonImdb());
    }

    @GetMapping("top250")
    public ResponseEntity<List<ImdbMovieDto>> get250Page() {
        return ResponseEntity.ok(imbdClient.getTop250Imdb());
    }

    @GetMapping("topTV")
    public ResponseEntity<List<ImdbMovieDto>> get250TVPage() {
        return ResponseEntity.ok(imbdClient.getTopTvImdb());
    }

    @GetMapping("movieImbd_details/{movieId}")
    public ResponseEntity<ImbdMovieDetailsDto> getMovieImbdDetails(@PathVariable String movieId) {
        return ResponseEntity.ok(imbdClient.getMovieDetailsImbd(movieId));
    }

    @GetMapping("search/{content}")
    public ResponseEntity<List<ImdbMovieDto>> searchThis(@PathVariable String content) {
        return ResponseEntity.ok(imbdClient.searchMovies(content));
    }

    @Autowired
    private UtellyClient utellyClient;

    @GetMapping("/watchmovie/{imdbId}")
    public ResponseEntity<List<LocationWatch>> getSiteList(@PathVariable String imdbId) {
        return ResponseEntity.ok(utellyClient.getMovieWatch(imdbId));
    }

}
