package com.imdbmovie.kodillafinalproject.imdbMovie.utellyService.client;

import com.imdbmovie.kodillafinalproject.imdbMovie.utellyService.domain.LocationWatch;
import com.imdbmovie.kodillafinalproject.imdbMovie.utellyService.domain.UtellyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UtellyClient {

    private final RestTemplate restTemplate;

    @Value("${utelly.api.endpoint}")
    private String uttleyApiEndpoint;

    @Value("${utelly.app.source}")
    private String source;

    @Value("${utelly.key}")
    private String key;

    public List<LocationWatch> getMovieWatch(String movieImdbId) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(uttleyApiEndpoint)
                .queryParam("source_id", movieImdbId)
                .queryParam("source", source)
                .build().encode().toUri();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("X-RapidAPI-Key", key);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<UtellyResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, UtellyResponse.class);
        UtellyResponse uResponse = response.getBody();
        return uResponse.getLocations().getWatchList();
    }
}
