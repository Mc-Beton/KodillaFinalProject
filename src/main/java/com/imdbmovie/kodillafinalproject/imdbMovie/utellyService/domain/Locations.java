package com.imdbmovie.kodillafinalproject.imdbMovie.utellyService.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Locations {

    @JsonProperty("locations")
    private List<LocationWatch> watchList;
}
