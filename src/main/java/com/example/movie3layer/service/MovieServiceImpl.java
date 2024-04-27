package com.example.movie3layer.service;

import com.example.movie3layer.dto.*;
import com.example.movie3layer.exception.NotFoundException;
import com.example.movie3layer.mapper.CommentToCommentDTO;
import com.example.movie3layer.mapper.MovieToMovieDTO;
import com.example.movie3layer.model.*;
import com.example.movie3layer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieToMovieDTO movieToMovieDTO;
    @Autowired
    private CommentToCommentDTO commentToCommentDTO;
    private List<MovieDTO> movieDTOs;
    private MetaData metaData;
    private MapStringObject objectMap;
    @Autowired
    private EpisodesRepository episodesRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public MapStringObject getAllMovie(String genre, String type, int page) {
        Page<Movie> moviePage;
        Pageable pageable = PageRequest.of(page, 100);
        if (genre.equals("") && type.equals("")) {
            moviePage = movieRepository.findAll(pageable);
            if (moviePage.getContent().size() > 0) {
                metaData = new MetaData(
                        moviePage.getTotalElements(),
                        pageable.getPageNumber() + 1,
                        moviePage.getTotalPages()
                );
                objectMap = new MapStringObject(
                        moviePage.stream().map(movieToMovieDTO::apply).collect(Collectors.toList()),
                        metaData
                );
                return objectMap;
            }
            throw new NotFoundException("Không tồn tại movie nào");
        } else { // genre != ""
            moviePage = movieRepository.getMoviesByGenreName(genre, type, pageable);
            if (moviePage.getContent().size() > 0) {
                metaData = new MetaData(
                        moviePage.getTotalElements(),
                        pageable.getPageNumber() + 1,
                        moviePage.getTotalPages()
                );
                objectMap = new MapStringObject(
                        moviePage.stream().map(movieToMovieDTO::MovieToMovieSlimDTO).collect(Collectors.toList()),
                        metaData
                );
                return objectMap;
            }
            throw new NotFoundException("Không tồn tại movie nào");
        }
    }

    @Override
    public MapStringObject getAllFavoriteByUser(String genre, String type, int page, int user_id) {
        Page<Movie> moviePage;
        Pageable pageable = PageRequest.of(page, 100);

        moviePage = movieRepository.getFavoriteByUser(pageable, user_id);
        if (moviePage.getContent().size() > 0) {
            metaData = new MetaData(
                    moviePage.getTotalElements(),
                    pageable.getPageNumber() + 1,
                    moviePage.getTotalPages()
            );
            objectMap = new MapStringObject(
                    moviePage.stream().map(movieToMovieDTO::MovieToMovieSlimDTO).collect(Collectors.toList()),
                    metaData
            );
            return objectMap;
        }
        throw new NotFoundException("Không tồn tại movie nào");
    }
    @Override
    public MapStringObject getAllWatchedByUser(String genre, String type, int page, int user_id) {
        Page<Movie> moviePage;
        Pageable pageable = PageRequest.of(page, 100);

        moviePage = movieRepository.getWatchedByUser(genre, type, pageable, user_id);
        if (moviePage.getContent().size() > 0) {
            metaData = new MetaData(
                    moviePage.getTotalElements(),
                    pageable.getPageNumber() + 1,
                    moviePage.getTotalPages()
            );
            objectMap = new MapStringObject(
                    moviePage.stream().map(movieToMovieDTO::MovieToMovieSlimDTO).collect(Collectors.toList()),
                    metaData
            );
            return objectMap;
        }
        throw new NotFoundException("Không tồn tại movie nào");
    }


    @Override
    public MovieDTO getMovieByID(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return movieToMovieDTO.apply(movieRepository.getMovieByMovieID(id));
        }
        throw new NotFoundException("Movie không tồn tại.");
    }

    @Override
    public MapStringObject searchMoviesWithForm(MovieSearchForm movieSearchForm) {
        int ratingEnd = 10;
        MovieSearchForm movieSearchForm1 = movieSearchForm;
        Page<Movie> moviePage;
        Pageable pageable = PageRequest.of(0, 100);
        if (movieSearchForm.getRatingRange() == 0) {
            ratingEnd = 5;
        } else if (movieSearchForm.getRatingRange() == 5) {
            ratingEnd = 7;
        } else if (movieSearchForm.getRatingRange() == 7) {
            ratingEnd = 9;
        } else if (movieSearchForm.getRatingRange() == 9) {
            ratingEnd = 10;
        } else if (movieSearchForm.getRatingRange() == 10) {
            ratingEnd = 10;
            movieSearchForm.setRatingRange(0);
        }
        if (movieSearchForm.getReleaseYearStart() == 0 && movieSearchForm.getReleaseYearEnd() == 0) {
            movieSearchForm.setReleaseYearStart(0);
            movieSearchForm.setReleaseYearEnd(1000000);
        }
        if (movieSearchForm.getGenres().equals("none")) {
            moviePage = movieRepository.searchMovieByFormSearch(
                    movieSearchForm.getMovieName(),
                    movieSearchForm.getRatingRange(),
                    ratingEnd,
                    movieSearchForm.getReleaseYearStart(),
                    movieSearchForm.getReleaseYearEnd(),
                    pageable
            );
        } else {
            moviePage = movieRepository.searchMovieByFormSearchWithGenres(
                    movieSearchForm.getMovieName(),
                    movieSearchForm.getGenres(),
                    movieSearchForm.getRatingRange(),
                    ratingEnd,
                    movieSearchForm.getReleaseYearStart(),
                    movieSearchForm.getReleaseYearEnd(),
                    pageable
            );
        }
        if (moviePage.getContent().size() > 0) {
            metaData = new MetaData(
                    moviePage.getTotalElements(),
                    pageable.getPageNumber() + 1,
                    moviePage.getTotalPages()
            );
            objectMap = new MapStringObject(
                    moviePage.stream().map(movieToMovieDTO::MovieToMovieSlimDTO).collect(Collectors.toList()),
                    metaData
            );
            return objectMap;
        }

        throw new NotFoundException("Không có kết quả nào khớp với yêu cầu tìm kiếm của bạn");
    }

    @Override
    public List<MovieDTO> getMoviesByRated() {
        List<Movie> movies = movieRepository.getMoviesByRated();
        return movies.stream().map(movieToMovieDTO::apply).collect(Collectors.toList());
    }
//    thêm movie qua API
    @Override
    public Movie saveMovie(MovieAddData movieAddData) {
        MovieAdd movieAdd = movieAddData.getMovie();

        List<EpisodesDTO> list = movieAddData.getEpisodes().get(0).getEpisodesList();
        List<Episodes> episodesList = new ArrayList<>();

        list.forEach(episodesDTO -> {
            Episodes episodes = new Episodes(
                    episodesDTO.getName(),
                    episodesDTO.getSlug(),
                    episodesDTO.getFilename(),
                    episodesDTO.getLink()
            );

            episodesList.add(episodes);
        });
//        thêm tất cả các bản ghi tập phim vào db
//        episodesRepository.saveAll(episodesList);

        Movie movie = new Movie(
                movieAdd.getName(),
                movieAdd.getSlug(),
                Integer.toString(movieAdd.getYear()),
                Integer.toString(movieAdd.getYear()),
                (int) (Math.random() * 5) + 5 + ((int) (Math.random() * 5) + 5) / 10,
//                null,
//                null,
//                null,
//                null,
                movieAdd.getType(),
                episodesList,
//                null,
                Integer.toString(movieAdd.getYear()),
                movieAdd.getTime(),
                movieAdd.getLang(),
                "England",
                null,
                movieAdd.getContent(),
                movieAdd.getPosterUrl(),
                movieAdd.getThumbUrl(),
                movieAdd.getTrailerUrl().substring(movieAdd.getTrailerUrl().lastIndexOf('=') + 1)
        );
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie saveMovieAdmin(MovieDTO movieDTO) {
        MovieDTO movieAdd = movieDTO;

        List<EpisodesDTO> list = movieAdd.getEpisodes();
        List<Episodes> episodesList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();
        List<Actor> actorList = new ArrayList<>();

//        Lấy danh sách thể loại theo tên từng thể loại (genre)
        if (movieDTO.getGenres() != null) {
            movieDTO.getGenres().forEach(genre -> {
                Genre genre1 = genreRepository.getGenreByGenreNameVie(genre);
                genreList.add(genre1);
            });
        }

//        Lấy danh sách actor theo tên từng actor
        if (movieDTO.getActors() != null) {
            movieDTO.getActors().forEach(actor -> {
                Actor actor1 = actorRepository.getActorByName(actor);
                actorList.add(actor1);
            });
        }

        if (list != null) {
            list.forEach(episodesDTO -> {
                Episodes episodes = new Episodes(
                        episodesDTO.getId(),
                        episodesDTO.getName(),
                        episodesDTO.getSlug(),
                        episodesDTO.getFilename(),
                        episodesDTO.getLink()
                );
                episodesList.add(episodes);
            });
        }


//        xóa 2 bảng episodes và movie_episodes
//        if (movieAdd.getMovie_id() != 0) {
//            episodesRepository.deleteEpisodes(movieAdd.getMovie_id());
//        }

        String new_id = movieRepository.getMaxID();

        Movie movie = new Movie(
            movieAdd.getMovie_id(),
            movieAdd.getName(),
            movieAdd.getSlug(),
            movieAdd.getYear(),
            movieAdd.getYear(),
            movieAdd.getRated(),
            actorList,
            genreList,
            movieAdd.getType(),
            episodesList,
            movieAdd.getYear(),
            movieAdd.getRuntime(),
            movieAdd.getCountry(),
            movieAdd.getCountry(),
            movieAdd.getAwards(),
            movieAdd.getContent(),
            movieAdd.getPoster(),
            movieAdd.getThumb(),
            movieAdd.getTrailer(),
            0
        );
        System.out.println(movie);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Boolean updateFavorite(String movie_id, String user_id) {
        try {
            Integer check = movieRepository.checkExistFavorite(Integer.parseInt(movie_id), Integer.parseInt(user_id));
            if (check == 1) {
                movieRepository.deleteFavorite(Integer.parseInt(movie_id), Integer.parseInt(user_id));
            } else if (check == 0) {
                movieRepository.insertFavorite(Integer.parseInt(movie_id), Integer.parseInt(user_id));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean checkFavorite(String movie_id, String user_id) {
        Integer check = movieRepository.checkExistFavorite(Integer.parseInt(movie_id), Integer.parseInt(user_id));
        if (check == 1) {
            return true;
        } else if (check == 0) {
            return false;
        }
        return false;
    }

    @Override
    public MapStringObject getMovieByChoice(String choice, String type, int page) {
        List<Movie> moviePage;
        Pageable pageable = PageRequest.of(page, 100);
//
        if (choice.equals("year")) {
            moviePage = movieRepository.getMovieYear(type, 10);
            if (moviePage.size() > 0) {
                metaData = new MetaData(
                        0L,
                        pageable.getPageNumber() + 1,
                        0
                );
                objectMap = new MapStringObject(
                        moviePage.stream().map(movieToMovieDTO::MovieToMovieSlimDTO).collect(Collectors.toList()),
                        metaData
                );
                return objectMap;
            }
            throw new NotFoundException("Không tồn tại movie nào");
        } else if (choice.equals("recommended")) {
            moviePage = movieRepository.findMovieRecommended();
            if (moviePage.size() > 0) {
                metaData = new MetaData(
                        0L,
                        0,
                        0
                );
                objectMap = new MapStringObject(
                        moviePage.stream().map(movieToMovieDTO::MovieToMovieSlimDTO).collect(Collectors.toList()),
                        metaData
                );
                return objectMap;
            }
        }

        return null;
    }

    @Override
    public MapStringObject getMovieRecommended() {


        return null;
    }

    @Override
    public Boolean updateView(int movie_id, int user_id) {
        try {
            movieRepository.updateViewMovie(movie_id);
            movieRepository.updateViewUser(movie_id, user_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void removeMovie(String movie_id) {
        try {
            movieRepository.deleteById(Integer.parseInt(movie_id));
        } catch (Exception e) {
        }
    }

//    @Override
//    public List<CommentDTO> getCommentsByMovieID(Integer movie_id) {
//        List<Comment> comments = commentRepository.getCommentByCommentID(movie_id);
//
//        return comments.stream().map(commentToCommentDTO::apply).collect(Collectors.toList());
//    }

}
