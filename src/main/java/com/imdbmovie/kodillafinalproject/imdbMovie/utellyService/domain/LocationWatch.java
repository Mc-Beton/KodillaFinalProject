package com.imdbmovie.kodillafinalproject.imdbMovie.utellyService.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationWatch {

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String siteUrl;

    @JsonProperty("icon")
    private String iconSite;
}
