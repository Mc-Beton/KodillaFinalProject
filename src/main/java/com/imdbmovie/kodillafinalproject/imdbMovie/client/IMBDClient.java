package com.imdbmovie.kodillafinalproject.imdbMovie.client;

import com.imdbmovie.kodillafinalproject.imdbMovie.domain.ImbdMovieDetailsDto;
import com.imdbmovie.kodillafinalproject.imdbMovie.domain.ImdbMovieDto;
import com.imdbmovie.kodillafinalproject.imdbMovie.domain.ResponseDtoImdb;
import com.imdbmovie.kodillafinalproject.imdbMovie.domain.SearchResponseImdb;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class IMBDClient {

    private final RestTemplate restTemplate;

    @Value("${imbd.api.endpoint.prod}")
    private String imbdApiEndpoint;

    @Value("${imbd.app.key}")
    private String imbdKey;

    public List<ImdbMovieDto> getMoviesImdb() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"MostPopularMovies/" + imbdKey)
                .build().encode().toUri();

        ResponseDtoImdb imbdResponse = restTemplate.getForObject(url, ResponseDtoImdb.class);

        assert imbdResponse != null;
        return imbdResponse.getItems();
    }

    public List<ImdbMovieDto> getComingSoonImdb() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"ComingSoon/" + imbdKey)
                .build().encode().toUri();

        ResponseDtoImdb imbdResponse = restTemplate.getForObject(url, ResponseDtoImdb.class);

        assert imbdResponse != null;
        return imbdResponse.getItems();
    }

    public List<ImdbMovieDto> getTop250Imdb() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"Top250Movies/" + imbdKey)
                .build().encode().toUri();

        ResponseDtoImdb imbdResponse = restTemplate.getForObject(url, ResponseDtoImdb.class);

        assert imbdResponse != null;
        return imbdResponse.getItems();
    }

    public List<ImdbMovieDto> getTopTvImdb() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"Top250TVs/" + imbdKey)
                .build().encode().toUri();

        ResponseDtoImdb imbdResponse = restTemplate.getForObject(url, ResponseDtoImdb.class);

        assert imbdResponse != null;
        return imbdResponse.getItems();
    }

    public ImbdMovieDetailsDto getMovieDetailsImbd(String movieId) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"Title/" + imbdKey + "/" + movieId)
                .build().encode().toUri();

        return restTemplate.getForObject(url, ImbdMovieDetailsDto.class);
    }

    public List<ImdbMovieDto> searchMovies(String queryContent) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"Search/" + imbdKey + "/" + queryContent)
                .build().encode().toUri();

        SearchResponseImdb responseDtoImdb = restTemplate.getForObject(url, SearchResponseImdb.class);

        assert responseDtoImdb != null;
        return responseDtoImdb.getResults();
    }

    public List<ImdbMovieDto> getUserMovieList(Set<String> movies) {
        List<ImdbMovieDto> movieList = new ArrayList<>();
        movies
                .forEach(m -> {
                    URI url = UriComponentsBuilder
                            .fromHttpUrl(imbdApiEndpoint +"Title/" + imbdKey + "/" + m)
                            .build().encode().toUri();
                    movieList.add(restTemplate.getForObject(url, ImdbMovieDto.class));
                });
        return movieList;
    }
}