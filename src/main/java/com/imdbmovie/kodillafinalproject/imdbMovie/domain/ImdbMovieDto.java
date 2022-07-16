package com.imdbmovie.kodillafinalproject.imdbMovie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImdbMovieDto {

    @JsonProperty("id")
    private String imbdId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("image")
    private String image;

    @JsonProperty("year")
    private String year;
}
