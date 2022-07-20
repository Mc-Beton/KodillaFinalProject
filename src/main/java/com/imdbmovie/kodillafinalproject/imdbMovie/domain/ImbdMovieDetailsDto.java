package com.imdbmovie.kodillafinalproject.imdbMovie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImbdMovieDetailsDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("image")
    private String image;

    @JsonProperty("plot")
    private String plot;
}
