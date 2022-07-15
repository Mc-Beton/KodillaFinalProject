package com.imdbmovie.kodillafinalproject.rating.controller;

import com.google.gson.Gson;
import com.imdbmovie.kodillafinalproject.imdbMovie.config.CoreConfiguration;
import com.imdbmovie.kodillafinalproject.rating.domain.Rating;
import com.imdbmovie.kodillafinalproject.rating.domain.dto.RatingDto;
import com.imdbmovie.kodillafinalproject.rating.mapper.RatingMapper;
import com.imdbmovie.kodillafinalproject.rating.service.RatingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(RatingController.class)
@Import(CoreConfiguration.class)
class RatingControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @MockBean
    private RatingMapper ratingMapper;

    @Test
    void createNewRating() throws Exception {
        //Given
        Rating rating = new Rating();
        RatingDto ratingDto = new RatingDto("tt123456", "username", 8);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(ratingDto);

        when(ratingMapper.mapToRating(ratingDto)).thenReturn(rating);
        when(ratingService.saveNewRating(rating)).thenReturn(rating);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/rating")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void getAllRatingsTest() throws Exception {
        //Given
        List<Rating> rating = List.of(new Rating());
        List<RatingDto> ratingDto = List.of(new RatingDto("tt123456", "username", 8));

        when(ratingMapper.mapToDtoList(rating)).thenReturn(ratingDto);
        when(ratingService.getAllRatings()).thenReturn(rating);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/rating/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId", Matchers.is("tt123456")));
    }

    @Test
    void getRatingOfMovieByUserTest() throws Exception {
        //Given
        Rating rating = new Rating();
        RatingDto ratingDto = new RatingDto("tt123456", "username", 8);

        when(ratingMapper.mapToRatingDto(rating)).thenReturn(ratingDto);
        when(ratingService.getRatingByUserAndMovie("tt123456", "username")).thenReturn(rating);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/rating/username/movie/tt123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId", Matchers.is("tt123456")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("username")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating", Matchers.is(8)));
    }

    @Test
    void getRatingsOfAMovieTest() throws Exception {
        //Given
        List<Rating> rating = List.of(new Rating());
        List<RatingDto> ratingDto = List.of(new RatingDto("tt123456", "username", 8));

        when(ratingMapper.mapToDtoList(rating)).thenReturn(ratingDto);
        when(ratingService.getRatingsByMovie("tt123456")).thenReturn(rating);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rating/movie/tt123456")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId", Matchers.is("tt123456")));
    }

    @Test
    void getRartingByIdTest() throws Exception {
        //Given
        Rating rating = new Rating();
        RatingDto ratingDto = new RatingDto("tt123456", "username", 8);

        when(ratingMapper.mapToRatingDto(rating)).thenReturn(ratingDto);
        when(ratingService.getRatingById(1L)).thenReturn(rating);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rating/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId", Matchers.is("tt123456")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("username")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating", Matchers.is(8)));
    }

    @Test
    void deleteRatingByIdTest() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/rating/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}