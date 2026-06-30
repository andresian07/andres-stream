package com.andres_play.domain.exceptions;

public class MovieDoesnotExistException extends RuntimeException{
    public MovieDoesnotExistException(Long id){
        super("la pelicula con el id: " + id + " no existe");
    }
}
