package com.imdbmovie.kodillafinalproject.user.controller;

import com.imdbmovie.kodillafinalproject.exceptions.UserWithUsernameExistsException;
import com.imdbmovie.kodillafinalproject.imdbMovie.client.IMBDClient;
import com.imdbmovie.kodillafinalproject.imdbMovie.domain.ImdbMovieDto;
import com.imdbmovie.kodillafinalproject.user.domain.User;
import com.imdbmovie.kodillafinalproject.user.domain.UserDto;
import com.imdbmovie.kodillafinalproject.exceptions.UserNotFoundException;
import com.imdbmovie.kodillafinalproject.user.mapper.UserMapper;
import com.imdbmovie.kodillafinalproject.user.service.UserService;
import com.imdbmovie.kodillafinalproject.user.userFacade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final IMBDClient imbdClient;
    private final UserFacade userFacade;

    @GetMapping("/getUserDataLogin/{username}")
    public ResponseEntity<UserDto> getUserToLogin(@PathVariable String username) throws UserNotFoundException {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @GetMapping("/all-users/{name}")
    public ResponseEntity<List<UserDto>> getAllUsersByName(@PathVariable String name) {
        List<User> users = userService.getUserByName(name);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> makeNewUser(@RequestBody UserDto userDto) throws UserWithUsernameExistsException {
        userFacade.createNewUserFacade(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> editUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUserWithId(userDto);
        userService.saveNewUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(userService.getUserById(userId)));
    }

    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/addFriend/{friendId}")
    public ResponseEntity<Void> addFriend(@PathVariable Long userId, @PathVariable Long friendId) throws UserNotFoundException {
        userService.addFriend(userId, friendId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/friends/{userId}")
    public ResponseEntity<List<UserDto>> getFriendList(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDtoList(userService.findUserFriends(userId)));
    }

    @PutMapping(value = "/{userId}/addMovieToFav/{movieId}")
    public ResponseEntity<Void> addMovieToFavorite(@PathVariable String userId, @PathVariable String movieId) throws UserNotFoundException {
        userService.addMovieToFavorite(userService.getUserByUsername(userId).getId(), movieId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/addMovieToWatch/{movieId}")
    public ResponseEntity<Void> addMovieToWatch(@PathVariable String userId, @PathVariable String movieId) throws UserNotFoundException {
        userService.addMovieToFWatch(userService.getUserByUsername(userId).getId(), movieId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}/favoriteList")
    public ResponseEntity<List<ImdbMovieDto>> getFavoriteList(@PathVariable String userId) throws UserNotFoundException {
        return ResponseEntity.ok(imbdClient.getUserMovieList(userService.getUserByUsername(userId).getFavoriteList()));
    }

    @GetMapping(value = "/{userId}/toWatchList")
    public ResponseEntity<List<ImdbMovieDto>> getToWatchList(@PathVariable String userId) throws UserNotFoundException {
        return ResponseEntity.ok(imbdClient.getUserMovieList(userService.getUserByUsername(userId).getToWatchList()));
    }
}
