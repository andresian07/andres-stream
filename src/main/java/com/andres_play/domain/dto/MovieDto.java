// src/main/java/com/andres_play/web/dto/MovieDto.java
package com.andres_play.domain.dto;

import com.andres_play.domain.Genre;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovieDto(
        Long id,
        String title,        // antes: titulo
        Integer duration,
        Genre genre,        // antes: genero
        LocalDate releaseDate, // antes: fechaEstreno
        BigDecimal rating   // antes: calificacion
        // antes: estado
) {}