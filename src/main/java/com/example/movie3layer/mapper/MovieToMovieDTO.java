package com.example.movie3layer.mapper;

import com.example.movie3layer.dto.CelebrityDTO;
import com.example.movie3layer.dto.EpisodesDTO;
import com.example.movie3layer.dto.MovieDTO;
import com.example.movie3layer.dto.MovieSlimDTO;
import com.example.movie3layer.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MovieToMovieDTO implements Function<Movie, MovieDTO> {
    public MovieSlimDTO MovieToMovieSlimDTO(Movie movie) {
        return new MovieSlimDTO(
                movie.getMovieID(),
                movie.getName(),
                movie.getSlug(),
                Integer.toString(movie.getView()),
                movie.getContent(),
                movie.getYear(),
                movie.getRated(),
                movie.getRuntime(),
                movie.getType(),
                movie.getGenreList().stream().map(genre -> genre.getGenreVie()).collect(Collectors.toList()),
                movie.getLanguage(),
                movie.getPoster(),
                movie.getThumb(),
                episodesToEpisodesDTO(movie)
        );
    }
    private List<CelebrityDTO> PersonToCelebrityDTO(Movie movie) {
        List<CelebrityDTO> celebrityDTOs = new ArrayList<>();

        movie.getActorList().forEach(actor -> {
            CelebrityDTO celebrityDTO = new CelebrityDTO(
                    actor.getActorID(),
                    actor.getActorName(),
                    actor.getAvt()
            );
            celebrityDTOs.add(celebrityDTO);
        });


        return celebrityDTOs;
    }
    private List<EpisodesDTO> episodesToEpisodesDTO(Movie movie) {
        List<EpisodesDTO> episodesDTOS = new ArrayList<>();

        movie.getEpisodes().forEach(episodes -> {
            EpisodesDTO episodesDTO = new EpisodesDTO(
                    episodes.getEpisodes_id(),
                    episodes.getName(),
                    episodes.getSlug(),
                    episodes.getFile_name(),
                    episodes.getLink()
            );
            episodesDTOS.add(episodesDTO);
        });


        return episodesDTOS;
    }
    @Override
    public MovieDTO apply(Movie movie) {
        return new MovieDTO(
                movie.getMovieID(),
                movie.getName(),
                movie.getSlug(),
                Integer.toString(movie.getView()),
                movie.getYear(),
                movie.getEndYear(),
                movie.getRated(),
                movie.getReleased(),
                movie.getRuntime(),
                movie.getType(),
                movie.getGenreList().stream().map(genre -> genre.getGenreVie()).collect(Collectors.toList()),
                movie.getDirectorList().stream().map(director -> director.getDirectorName()).collect(Collectors.toList()),
                movie.getActorList().stream().map(actor -> actor.getActorName()).collect(Collectors.toList()),
                movie.getContent(),
                movie.getCountry(),
                movie.getAwards(),
                movie.getLanguage(),
                movie.getPoster(),
                movie.getThumb(),
                movie.getTrailer(),
                episodesToEpisodesDTO(movie)

        );
    }

    @Override
    public <V> Function<V, MovieDTO> compose(Function<? super V, ? extends Movie> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Movie, V> andThen(Function<? super MovieDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
