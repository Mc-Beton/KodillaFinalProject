package com.imdbmovie.kodillafinalproject.user.service;

import com.imdbmovie.kodillafinalproject.exceptions.UserNotFoundException;
import com.imdbmovie.kodillafinalproject.exceptions.UserWithUsernameExistsException;
import com.imdbmovie.kodillafinalproject.user.domain.User;
import com.imdbmovie.kodillafinalproject.user.domain.UserDto;
import com.imdbmovie.kodillafinalproject.user.mapper.UserMapper;
import com.imdbmovie.kodillafinalproject.user.userFacade.UserFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTestSuite {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserFacade userFacade;

    @Test
    void addNewUserTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        //When
        userService.saveNewUser(user);

        //Then
        assertTrue(userService.getUserByUsername("username").getName().equals("name"));

        //Clean up
        userService.deleteUserByUsername("username");
    }

    @Test
    void getUserByUserNameTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        //When
        userService.saveNewUser(user);

        //Then
        assertEquals(userService.getUserByUsername("username").getName(), user.getName());

        //Clean up
        userService.deleteUser(user.getId());
    }

    @Test
    void getAllUsersTest() {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user2 = new User.UserBuilder()
                .id(2L)
                .name("name")
                .surname("surname1")
                .username("username2")
                .password("password2")
                .phoneNumber("43522345")
                .email("asdfasdgf")
                .build();

        //When
        userService.saveNewUser(user);
        userService.saveNewUser(user2);

        //Then
        assertEquals(2, userService.getAllUsers().size());

        //Clean up
        userService.deleteUserByUsername(user.getUsername());
        userService.deleteUserByUsername(user2.getUsername());
    }

    @Test
    void updateUserTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        UserDto userDto = userMapper
                .mapToUserDto(userService.
                        getUserByUsername("username"));
        System.out.println(userDto.getId());

        //When
        userDto.setEmail("sdabssfg");
        userService.updateUser(userDto);

        //Then
        assertTrue(userService.getUserByUsername(user.getUsername()).getEmail().equals(userDto.getEmail()));

        //Clean up
        userService.deleteUserByUsername(user.getUsername());
    }

    @Test
    void addAndGetFriendsTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user2 = new User.UserBuilder()
                .id(2L)
                .name("name")
                .surname("surname1")
                .username("username2")
                .password("password")
                .phoneNumber("43522345")
                .email("asdfasdgf")
                .build();

        userService.saveNewUser(user);
        userService.saveNewUser(user2);

        //When
        Long id1 = userService.getUserByUsername(user.getUsername()).getId();
        Long id2 = userService.getUserByUsername(user2.getUsername()).getId();
        userService.addFriend(id1, id2);

        //Then
        assertEquals(1, userService.findUserFriends(id1).size());

        //Clean up
        userService.deleteUser(id1);
        userService.deleteUser(id2);
    }

    @Test
    void addAndGetFavoriteListTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        Long id = userService.getUserByUsername(user.getUsername()).getId();

        //When
        userService.addMovieToFavorite(id,"tt12321123");
        userService.addMovieToFavorite(id,"tt23521431");

        //Then
        assertEquals(2, userService.getFavMovieList("username").size());

        //Clean up
        userService.deleteUser(id);
    }

    @Test
    void addAndGetWatchListTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        Long id = userService.getUserByUsername(user.getUsername()).getId();

        //When
        userService.addMovieToFWatch(id,"tt12321123");
        userService.addMovieToFWatch(id,"tt23521431");

        //Then
        assertEquals(2, userService.getToWatchMovieList("username").size());

        //Clean up
        userService.deleteUser(id);
    }

    @Test
    void shouldMapToUserDto() {
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

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());

        //Clean up
        userService.deleteUser(user.getId());
    }

    @Test
    void shouldSaveNewUserFromUserDto() throws UserNotFoundException {
        //Given
        UserDto userDto = new UserDto( "name", "surname1", "username", "password", "24321545", "asdfadsg");

        //When
        userService.saveNewUser(userMapper.mapToUserWithId(userDto));

        //Then
        assertEquals(userService.getUserByUsername("username").getName(), userDto.getName());

        //Clean up
        userService.deleteUser(userService.getUserByUsername("username").getId());
    }

    @Test
    void shouldMapToUserDtoListTest() {
        //Given
        User user = new User.UserBuilder()
                .name("name")
                .surname("surname1")
                .username("username")
                .password("password")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user2 = new User.UserBuilder()
                .name("name")
                .surname("surname2")
                .username("username2")
                .password("password2")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        userService.saveNewUser(user2);

        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userService.getAllUsers());

        //Then
        assertEquals(2, userDtoList.size());

        //Clean up
        userService.deleteUser(user.getId());
        userService.deleteUser(user2.getId());
    }

    @Test
    void shouldThrowCreateExceptionTest() throws UserWithUsernameExistsException, UserNotFoundException {
        //Given
        UserDto userDto = new UserDto( "name", "surname1", "username", "password", "24321545", "asdfadsg");
        userFacade.createNewUserFacade(userDto);

        UserDto userDto2 = new UserDto( "name2", "surname2", "username", "password2", "2352346", "asgdag");

        //When & Then
        UserWithUsernameExistsException thrown = Assertions.assertThrows(UserWithUsernameExistsException.class, () -> {
            userFacade.createNewUserFacade(userDto2);
        });

        //Clean up
        userService.deleteUser(userService.getUserByUsername(userDto.getUsername()).getId());
    }

    @Test
    void shouldThrowUserNotFoundExceptionTest() throws UserNotFoundException {
        //Given
        //When & Then
        UserNotFoundException thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByUsername("username");
        });
    }
}