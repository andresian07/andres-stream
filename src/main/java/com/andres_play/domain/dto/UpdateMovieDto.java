// src/main/java/com/andres_play/web/dto/MovieDto.java
package com.andres_play.domain.dto;

import com.andres_play.domain.Genre;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateMovieDto(

        String title,        // antes: titulo
        LocalDate releaseDate,
        BigDecimal rating  // antes: fechaEstreno
        // antes: estado


) {}