package com.example.movie3layer.mapper;

import com.example.movie3layer.dto.GenreDTO;
import com.example.movie3layer.model.Genre;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GenreToGenreDTO implements Function<Genre, GenreDTO> {

    @Override
    public GenreDTO apply(Genre genre) {
        return new GenreDTO(
                genre.getGenreID(),
                genre.getGenre(),
                genre.getGenreVie()
        );
    }
}
