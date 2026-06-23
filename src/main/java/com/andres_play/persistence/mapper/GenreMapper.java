package com.andres_play.persistence.mapper;

import com.andres_play.domain.Genre;
import org.mapstruct.Named;

public class GenreMapper {

    // String -> Genre (se usa cuando LEES de la base de datos:
    // MovieEntity.genero viene como String, y necesitas convertirlo
    // al enum Genre para el MovieDto)
    @Named("stringToGenre")
    public static Genre stringToGenre(String genre) {
        if (genre == null) {
            return null;
        }

        return switch (genre) {
            case "Aventura" -> Genre.ADVENTURE;
            case "Anime" -> Genre.ANIME;
            case "Ciencia Ficción" -> Genre.SCI_FI;
            case "Acción" -> Genre.ACTION;
            case "Crimen" -> Genre.CRIME;
            case "Drama" -> Genre.DRAMA;
            case "Suspenso" -> Genre.THRILLER;
            case "Misterio" -> Genre.MYSTERY;
            case "Western" -> Genre.WESTERN;
            case "Animación" -> Genre.ANIMATION;
            default -> throw new IllegalArgumentException("Género no reconocido: " + genre);
        };
    }

    // Genre -> String (se usa cuando ESCRIBES en la base de datos:
    // si el usuario manda un MovieDto con Genre.ACTION, hay que
    // convertirlo de vuelta a "Acción" para guardarlo en la columna `genero`)
    //
    // Nota: aquí el switch es sobre "genre" que YA ES de tipo Genre,
    // por eso los case son ACTION, DRAMA, etc. sin escribir "Genre."
    // delante. Java ya sabe que estás comparando contra los valores
    // de ESE enum específico.
    @Named("genreToString")
    public static String genreToString(Genre genre) {
        if (genre == null) {
            return null;
        }

        return switch (genre) {
            case ADVENTURE -> "Aventura";
            case ANIME -> "Anime";
            case SCI_FI -> "Ciencia Ficción";
            case ACTION -> "Acción";
            case CRIME -> "Crimen";
            case DRAMA -> "Drama";
            case THRILLER -> "Suspenso";
            case MYSTERY -> "Misterio";
            case WESTERN -> "Western";
            case ANIMATION -> "Animación";
        };
    }
}