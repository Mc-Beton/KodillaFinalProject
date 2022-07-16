package com.imdbmovie.kodillafinalproject.user.userFacade;

import com.imdbmovie.kodillafinalproject.exceptions.UserWithUsernameExistsException;
import com.imdbmovie.kodillafinalproject.user.domain.User;
import com.imdbmovie.kodillafinalproject.user.domain.UserDto;
import com.imdbmovie.kodillafinalproject.user.mapper.UserMapper;
import com.imdbmovie.kodillafinalproject.user.repository.UserRepository;
import com.imdbmovie.kodillafinalproject.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createNewUserFacade(UserDto userDto) throws UserWithUsernameExistsException {
        log.info("Process started: Create new user");
        Optional<User> user = userRepository.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
            log.info("User with this username exists. Process terminated");
            throw new UserWithUsernameExistsException();
        } else {
            log.info("Creating new user with username: " + userDto.getUsername());
            return userService.saveNewUser(userMapper.mapToUserWithId(userDto));
        }
    }
}
