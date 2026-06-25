package com.andres_play.web.exceptions;

import com.andres_play.domain.exceptions.MovieAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MovieAlreadyExistException.class)
    public ResponseEntity<Error> handleException(MovieAlreadyExistException ex){
        Error error = new Error("movie-already-exists",ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
