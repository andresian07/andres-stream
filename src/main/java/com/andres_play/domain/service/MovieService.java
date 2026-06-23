package com.andres_play.domain.service;

import com.andres_play.domain.dto.MovieDto;
import com.andres_play.domain.dto.UpdateMovieDto;
import com.andres_play.domain.repository.MovieRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service marca esta clase como un bean que contiene LÓGICA DE NEGOCIO.
// Es lo mismo que @Component a nivel técnico, pero le dice a cualquiera
// que lea el código "aquí vive la lógica, no el acceso a datos ni el HTTP".
@Service
public class MovieService {

    // OJO: el tipo aquí es la INTERFACE MovieRepository, no la clase
    // CrudMovieEntity ni MovieEntityRepository. Esto es la clave de todo
    // el arreglo: MovieService ya no sabe que existe JPA, PostgreSQL,
    // ni siquiera que existe Spring Data. Solo sabe "tengo algo que me
    // puede dar una lista de MovieDto".
    private final MovieRepository movieRepository;

    // Cuando Spring arranca la aplicación, busca qué clase implementa
    // MovieRepository. Encuentra MovieEntityRepository (porque tiene
    // "implements MovieRepository" y está anotada con @Repository) y
    // la inyecta aquí automáticamente. Si en el futuro creas otra
    // implementación (ej. una que use MongoDB), solo cambias qué bean
    // implementa la interface, y MovieService no se entera ni cambia.
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Este método simplemente delega la llamada al repositorio.
    // Aquí, en el futuro, es donde meterías lógica de negocio real:
    // por ejemplo, filtrar películas inactivas, ordenar por fecha,
    // aplicar reglas de paginación, etc. Hoy no hace falta porque
    // el caso es simple, pero esta capa existe para crecer sin
    // tocar el Controller ni el Repository.

    @Tool("busca todas las peliculas que existan dentro de la plataforma")
    public List<MovieDto> getAll() {
        return movieRepository.getAll();
    }

    public MovieDto getById(Long id) {
        return movieRepository.getById(id);
    }

    public MovieDto add(MovieDto movieDto) {
        this.movieRepository.save(movieDto);
        return movieDto;
    }

    public MovieDto update(Long id, UpdateMovieDto updateMovieDto) {
        return this.movieRepository.update(id, updateMovieDto);
    }

    public MovieDto delete(Long id){
        return this.movieRepository.delete(id);
    }
}