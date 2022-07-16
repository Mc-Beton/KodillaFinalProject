package com.imdbmovie.kodillafinalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundHandler(UserNotFoundException e) {
        return new ResponseEntity<>("User with given parameters doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<Object> ratingNotFoundHandler(RatingNotFoundException e) {
        return new ResponseEntity<>("Rating is hard to find", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> ratingNotFoundHandler(PostNotFoundException e) {
        return new ResponseEntity<>("Post doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserWithUsernameExistsException.class)
    public ResponseEntity<Object> usernameUsedException(UserWithUsernameExistsException e) {
        return new ResponseEntity<>("Username in use, pick another username", HttpStatus.BAD_REQUEST);
    }
}
