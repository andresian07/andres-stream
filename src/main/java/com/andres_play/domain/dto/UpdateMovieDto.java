// src/main/java/com/andres_play/web/dto/MovieDto.java
package com.andres_play.domain.dto;

import com.andres_play.domain.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateMovieDto(

        @NotBlank(message = "el titulo es obligatorio")
        String title,        // antes: titulo

        @PastOrPresent(message = "la fecha de lanzamiento debe ser anterior o igual a la fecha actual")
        LocalDate releaseDate,

        @Min(value = 0,message = "el rating no puede ser menor a 0")
        @Max(value = 10, message = "el rating no puede ser mayor a 10")
        BigDecimal rating  // antes: fechaEstreno
        // antes: estado


) {}