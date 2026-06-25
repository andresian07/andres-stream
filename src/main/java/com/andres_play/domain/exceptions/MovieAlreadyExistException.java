package com.andres_play.domain.exceptions;

public class MovieAlreadyExistException extends RuntimeException {
    public MovieAlreadyExistException(String movieTitle){
        super("la pelicula " + movieTitle + " ya existe");
    }

}
