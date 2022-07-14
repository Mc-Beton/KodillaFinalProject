package com.imdbmovie.kodillafinalproject.user.controller;

import com.google.gson.Gson;
import com.imdbmovie.kodillafinalproject.imdbMovie.client.IMBDClient;
import com.imdbmovie.kodillafinalproject.imdbMovie.config.CoreConfiguration;
import com.imdbmovie.kodillafinalproject.user.domain.User;
import com.imdbmovie.kodillafinalproject.user.domain.UserDto;
import com.imdbmovie.kodillafinalproject.user.mapper.UserMapper;
import com.imdbmovie.kodillafinalproject.user.service.UserService;
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
@WebMvcTest(UserController.class)
@Import(CoreConfiguration.class)
class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserMapper mapper;

    @MockBean
    private IMBDClient imbdClient;

    @Test
    void createUserTest() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "name", "surname1", "username", "password", "24321545", "asdfadsg");
        User users = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        when(mapper.mapToUserWithId(userDto)).thenReturn(users);
        when(service.saveNewUser(users)).thenReturn(users);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void getAllUsersByNameTest() throws Exception {
        //Given
        List<UserDto> userDto = List.of(new UserDto(1L, "name", "surname1", "username", "password", "24321545", "asdfadsg"));
        List<User> users = List.of(new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build());

        when(service.getUserByName("name")).thenReturn(users);
        when(mapper.mapToUserDtoList(users)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/all-users/name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("username")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")));
    }

    @Test
    void getUserByIdTest() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "name", "surname1", "username", "password", "24321545", "asdfadsg");
        User users = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        when(service.getUserById(1L)).thenReturn(users);
        when(mapper.mapToUserDto(users)).thenReturn(userDto);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")));
    }
}