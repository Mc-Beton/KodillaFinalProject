package com.imdbmovie.kodillafinalproject.imdbMovie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDtoImdb {

    @JsonProperty("items")
    private List<ImdbMovieDto> items;
}
