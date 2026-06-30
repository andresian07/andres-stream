package com.andres_play.web.controller;

import com.andres_play.domain.dto.SuggestRequestDto;
import com.andres_play.domain.dto.UpdateMovieDto;
import com.andres_play.domain.service.AndresStreamAiService;
import com.andres_play.domain.service.MovieService;
import com.andres_play.domain.dto.MovieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// MovieController.java — corregido
@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Operacion about movies")
public class MovieController {

    private final MovieService movieService;
    private final AndresStreamAiService aiService;

    public MovieController(MovieService movieService, AndresStreamAiService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
    }

    @GetMapping
    public List<MovieDto> getAll(){
        return movieService.getAll();
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Get a movie by id",
            description = "retorna la pelicula que coincida con el id",
            responses = {
                @ApiResponse(responseCode = "200", description = "cuando la pelicula sea encontrada"),
                @ApiResponse(responseCode = "404", description = "pelicula no encontrada", content = @Content)

            }
    )
    public ResponseEntity<MovieDto> getById(@Parameter(description = "identificador de la pelicula a recuperar", example = "15") @PathVariable Long id){
        MovieDto movieDto = movieService.getById(id);
        if (movieDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMovieSuggestion(@RequestBody SuggestRequestDto suggestRequestDto){
        return ResponseEntity.ok(this.aiService.generateMoviesSuggestion(suggestRequestDto.userPreference()));
    }

    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody @Valid UpdateMovieDto updateMovieDto){
        return ResponseEntity.ok(this.movieService.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.movieService.delete(id); // Ejecuta el borrado que devuelve void
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}