package com.andres_play.persistence;

import com.andres_play.domain.dto.MovieDto;
import com.andres_play.domain.dto.UpdateMovieDto;
import com.andres_play.domain.exceptions.MovieAlreadyExistException;
import com.andres_play.domain.exceptions.MovieDoesnotExistException;
import com.andres_play.domain.repository.MovieRepository;
import com.andres_play.persistence.crud.CrudMovieEntity;
import com.andres_play.persistence.entity.MovieEntity;
import com.andres_play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository le dice a Spring: "esta clase accede a datos, créala como bean
// y guárdala en el contenedor para poder inyectarla donde se necesite".
// Es funcionalmente parecido a @Component, pero @Repository comunica
// la intención (esta clase es la puerta de entrada a la persistencia).
@Repository

// implements MovieRepository -> esta clase CUMPLE el contrato definido en
// domain/repository/MovieRepository.java. Es la pieza que conecta el mundo
// del dominio (que no sabe nada de JPA) con el mundo real de la base de datos.
public class MovieEntityRepository implements MovieRepository {

    // Dependencia 1: el repositorio JPA que sabe hablar con PostgreSQL.
    // CrudMovieEntity extiende CrudRepository<MovieEntity, Long>, así que
    // ya trae métodos como findAll(), save(), deleteById(), etc. gratis.
    private final CrudMovieEntity crudMovieEntity;

    // Dependencia 2: el traductor Entity -> Dto generado por MapStruct.
    // Recuerda que MovieMapper es una interface; en tiempo de compilación
    // MapStruct genera MovieMapperImpl y Spring inyecta esa implementación aquí.
    private final MovieMapper movieMapper;

    // Inyección por constructor: Spring ve que esta clase necesita un
    // CrudMovieEntity y un MovieMapper, y como ambos ya son beans
    // (uno por extender CrudRepository, el otro por @Mapper(componentModel="spring")),
    // Spring los crea y los pasa aquí automáticamente. Tú nunca escribes "new".
    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    // @Override confirma que este método existe en la interface MovieRepository.
    // Si el profesor cambia la firma del método en la interface y aquí no
    // coincide, el compilador avisa inmediatamente (en vez de fallar en runtime).
    @Override
    public List<MovieDto> getAll() {

        return movieMapper.toDtoList(crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(Long id){
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        return this.movieMapper.toDto(movieEntity);
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        if (this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null){
            throw new MovieAlreadyExistException(movieDto.title());
        }

        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("A");


        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto update(Long id, UpdateMovieDto updateMovieDto) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        if (movieEntity != null){

            this.movieMapper.updateEntityFromDto(updateMovieDto,movieEntity);
            return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));


        }


        throw new MovieDoesnotExistException(id);



    }

    @Override
    public MovieDto delete(Long id) {
        // 1. La buscas primero para tener sus datos en memoria
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if (movieEntity != null) {
            // 2. La borras de la base de datos (devuelve void, así que no se asigna a nada)
            this.crudMovieEntity.deleteById(id);

            // 3. Traduces la entidad que guardaste en el paso 1 a DTO y la devuelves
            return this.movieMapper.toDto(movieEntity);
        }


        throw new MovieDoesnotExistException(id);
    }

}