package com.imdbmovie.kodillafinalproject.user.controller;

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
@WebMvcTest(UserControllerManagement.class)
@Import(CoreConfiguration.class)
class UserControllerManagementTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserMapper mapper;

    @Test
    void getAllUsersTest() throws Exception {
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

        when(service.getAllUsers()).thenReturn(users);
        when(mapper.mapToUserDtoList(users)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/management/all-users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("username")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")));
    }
}