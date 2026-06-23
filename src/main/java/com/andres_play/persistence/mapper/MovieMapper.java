package com.andres_play.persistence.mapper;

import com.andres_play.domain.dto.UpdateMovieDto;
import com.andres_play.persistence.entity.MovieEntity;
import com.andres_play.domain.dto.MovieDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface MovieMapper {
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "genero", target = "genre", qualifiedByName = "stringToGenre")
    @Mapping(source = "fechaEstreno", target = "releaseDate")
    @Mapping(source = "calificacion", target = "rating")
    MovieDto toDto(MovieEntity entity);

    List<MovieDto> toDtoList(Iterable<MovieEntity> all);

    @InheritInverseConfiguration
    @Mapping(source = "genre", target = "genero", qualifiedByName = "genreToString")
    MovieEntity toEntity(MovieDto movieDto);


    List<MovieEntity> toEntityList(Iterable<MovieDto> all);

    @Mapping(target = "titulo", source = "title")
    @Mapping(target = "fechaEstreno", source = "releaseDate")
    @Mapping(target = "calificacion", source = "rating")
    MovieEntity updateEntityFromDto(UpdateMovieDto updateMovieDto, @MappingTarget MovieEntity movieEntity);
}


