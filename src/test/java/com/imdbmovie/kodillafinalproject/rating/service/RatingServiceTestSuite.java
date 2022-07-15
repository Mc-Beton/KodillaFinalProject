package com.imdbmovie.kodillafinalproject.rating.service;

import com.imdbmovie.kodillafinalproject.exceptions.RatingNotFoundException;
import com.imdbmovie.kodillafinalproject.rating.domain.AverageRating;
import com.imdbmovie.kodillafinalproject.rating.domain.Rating;
import com.imdbmovie.kodillafinalproject.rating.domain.dto.RatingDto;
import com.imdbmovie.kodillafinalproject.rating.mapper.RatingMapper;
import com.imdbmovie.kodillafinalproject.user.domain.User;
import com.imdbmovie.kodillafinalproject.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RatingServiceTestSuite {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingMapper ratingMapper;

    @Autowired
    private UserService userService;

    @Test
    void saveNewUserRatingTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        Rating rating = new Rating("tt123456", user, 6);

        //When
        ratingService.saveNewRating(rating);

        //Then
        assertEquals(1, ratingService.getAllRatings().size());

        //Clean up
        ratingService.deleteRating(rating.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    void getMovieRatingsTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username1")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user2 = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username2")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user3 = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username3")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        userService.saveNewUser(user2);
        userService.saveNewUser(user3);
        Rating rating = new Rating("tt123456", user, 6);
        Rating rating1 = new Rating("tt123456", user2,5);
        Rating rating2 = new Rating("tt132456", user3, 8);

        //When
        ratingService.saveNewRating(rating);
        ratingService.saveNewRating(rating1);
        ratingService.saveNewRating(rating2);

        //Then
        assertEquals(2, ratingService.getRatingsByMovie("tt123456").size());
        assertEquals(1, ratingService.getRatingsByMovie("tt132456").size());

        //Clean up
        ratingService.deleteRating(rating.getId());
        ratingService.deleteRating(rating1.getId());
        ratingService.deleteRating(rating2.getId());
        userService.deleteUser(user.getId());
        userService.deleteUser(user2.getId());
        userService.deleteUser(user3.getId());
    }

    @Test
    void getOneRatingTest() throws RatingNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        Rating rating = new Rating("tt123456", user, 6);

        //When
        ratingService.saveNewRating(rating);

        //Then
        assertEquals(6, ratingService.getRatingById(rating.getId()).getMovieRate());
        assertEquals(6, ratingService.getRatingByUserAndMovie("tt123456", "username").getMovieRate());

        //Clean up
        ratingService.deleteRating(rating.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    void calculateAvgOfMovieTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username1")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user2 = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username2")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user3 = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username3")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        userService.saveNewUser(user2);
        userService.saveNewUser(user3);
        Rating rating = new Rating("tt123456", user, 6);
        Rating rating1 = new Rating("tt123456", user2,5);
        Rating rating2 = new Rating("tt132456", user3, 8);

        //When
        ratingService.saveNewRating(rating);
        ratingService.saveNewRating(rating1);
        ratingService.saveNewRating(rating2);

        //Then
        assertEquals(5.5, ratingService.calculateAvgRating("tt123456"));
        assertEquals(8, ratingService.calculateAvgRating("tt132456"));

        //Clean up
        ratingService.deleteRating(rating.getId());
        ratingService.deleteRating(rating1.getId());
        ratingService.deleteRating(rating2.getId());
        userService.deleteUser(user.getId());
        userService.deleteUser(user2.getId());
        userService.deleteUser(user3.getId());
    }

    @Test
    void saveNewAndGetAverageRatingTest() {
        //Given
        AverageRating avg = new AverageRating("tt123456", 8.5);

        //When
        ratingService.saveNewCalculation(avg);

        //Then
        assertEquals(8.5, ratingService.getAvgRatingOfMovie("tt123456").getAverageRate());

        //Clean up
        ratingService.clearAvgRatings();
    }

    @Test
    void shouldGiveZeroIfMovieNotRatedTest() {
        //Given
        //When & Then
        assertEquals(0.0, ratingService.getAvgRatingOfMovie("tt123456").getAverageRate());
    }

    @Test
    void shouldMapToRatingDto() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username1")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        Rating rating = new Rating("tt123456", user, 8);

        //When
        RatingDto ratingDto = ratingMapper.mapToRatingDto(rating);

        //Then
        assertEquals(user.getUsername(), ratingDto.getUsername());
        assertEquals(8, ratingDto.getRating());
    }

    @Test
    void shouldMapToDtoListTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username1")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        List<Rating> rating = List.of(new Rating("tt123456", user, 8), new Rating("tt1234567", user, 8));

        //When
        List<RatingDto> ratingDto = ratingMapper.mapToDtoList(rating);

        //Then
        assertEquals(2, ratingDto.size());
    }

    @Test
    void shouldMapToRatingTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username1")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        RatingDto ratingDto = new RatingDto("tt123456", user.getUsername(), 8);

        //When
        Rating rating = ratingMapper.mapToRating(ratingDto);

        //Then
        assertEquals(rating.getUser().getUsername(), ratingDto.getUsername());

        //Clean up
        userService.deleteUser(user.getId());
    }
}