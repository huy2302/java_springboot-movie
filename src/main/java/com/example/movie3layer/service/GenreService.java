package com.example.movie3layer.service;

import com.example.movie3layer.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getAllGenre();
//    List<GenreDTO> getGenreByMovieID(String movie_id);
    GenreDTO getGenreByID (int id);
    Boolean saveMovieAdmin(GenreDTO genreDTO);
    void removeGenre(String id);
}
