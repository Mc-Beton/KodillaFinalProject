package com.imdbmovie.kodillafinalproject.rating.controller;

import com.imdbmovie.kodillafinalproject.imdbMovie.config.CoreConfiguration;
import com.imdbmovie.kodillafinalproject.rating.domain.AverageRating;
import com.imdbmovie.kodillafinalproject.rating.domain.dto.AverageRatingDto;
import com.imdbmovie.kodillafinalproject.rating.mapper.AverageRatingMapper;
import com.imdbmovie.kodillafinalproject.rating.service.RatingService;
import com.imdbmovie.kodillafinalproject.user.controller.UserControllerManagement;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(AvgRatingController.class)
@Import(CoreConfiguration.class)
class AvgRatingControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @MockBean
    private AverageRatingMapper mapper;

    @Test
    void getAvgOfMovieTest() throws Exception {
        //Given
        AverageRating averageRating = new AverageRating("tt123456", 5.5);
        AverageRatingDto ratingDto = new AverageRatingDto("tt123456", 5.5);

        when(mapper.mapToAvgRatingDto(averageRating)).thenReturn(ratingDto);
        when(ratingService.getAvgRatingOfMovie("tt123456")).thenReturn(averageRating);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/avgRating/tt123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId", Matchers.is("tt123456")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.averageRating", Matchers.is(5.5)));
    }
}