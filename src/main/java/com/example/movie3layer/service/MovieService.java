package com.example.movie3layer.service;

import com.example.movie3layer.dto.MovieAddData;
import com.example.movie3layer.dto.MovieDTO;
import com.example.movie3layer.model.MapStringObject;
import com.example.movie3layer.model.Movie;
import com.example.movie3layer.model.MovieSearchForm;

import java.util.List;

public interface MovieService {
    MapStringObject getAllMovie(String genre, String type, int page);
    MapStringObject getAllFavoriteByUser(String genre, String type, int page, int user_id);
    MapStringObject getAllWatchedByUser(String genre, String type, int page, int user_id);
    MovieDTO getMovieByID(int id);
    MapStringObject searchMoviesWithForm(MovieSearchForm movieSearchForm);
    List<MovieDTO> getMoviesByRated();
    Movie saveMovie(MovieAddData movieAddData);
    Movie saveMovieAdmin(MovieDTO movieDTO);
    Boolean updateFavorite(String movie_id, String user_id);
    Boolean checkFavorite(String movie_id, String user_id);
    MapStringObject getMovieByChoice(String choice, String type, int page);
    MapStringObject getMovieRecommended();
    Boolean updateView(int movie_id, int user_id);
    void removeMovie(String movie_id);
//    List<CommentDTO> getCommentsByMovieID (Integer movie_id);
}
