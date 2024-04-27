package com.example.movie3layer.repository;

import com.example.movie3layer.model.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("select m from Movie m where m.movieID = :id")
    Movie getMovieByMovieID(int id);
    @Query(value = "SELECT MAX(movie.movie_id) + 1 as new_id FROM movie", nativeQuery = true)
    String getMaxID();

    @Query(value = "SELECT DISTINCT movie.* FROM movie JOIN movie_genre ON movie_genre.movie_id = movie.movie_id JOIN genre ON  genre.genre_id = movie_genre.genre_id WHERE (LOWER(genre.genre) LIKE LOWER(CONCAT('%', :genre, '%')) OR LOWER(genre.genre_vie) LIKE LOWER(CONCAT('%', :genre, '%'))) AND movie.type LIKE LOWER(CONCAT('%', :type, '%'))", nativeQuery = true)
    Page<Movie> getMoviesByGenreName(@Param("genre") String genre, @Param("type") String type, Pageable pageable);
//    @Query(value = "SELECT * FROM movie.movie WHERE movie.year >= YEAR(NOW()) AND movie.year < YEAR(NOW()) + 1", nativeQuery = true)

    @Query(value = "SELECT * FROM movie.movie WHERE movie.type LIKE LOWER(CONCAT('%', :type, '%')) ORDER BY movie.year DESC LIMIT :limit", nativeQuery = true)
    List<Movie> getMovieYear(@Param("type") String type, @Param("limit") int limit);

    @Query(value = "SELECT * FROM movie WHERE movie.rated >= 7 ORDER BY movie.rated DESC LIMIT 10", nativeQuery = true)
    List<Movie> findMovieRecommended();

    @Query(value = "SELECT DISTINCT movie.* FROM movie JOIN favorite ON favorite.movie_id = movie.movie_id AND favorite.user_id = :user_id", nativeQuery = true)
    Page<Movie> getFavoriteByUser(Pageable pageable, @Param("user_id") int user_id);

    @Query(value = "SELECT DISTINCT movie.* FROM movie JOIN view ON view.movie_id = movie.movie_id JOIN movie_genre ON movie_genre.movie_id = movie.movie_id JOIN genre ON  genre.genre_id = movie_genre.genre_id WHERE (LOWER(genre.genre) LIKE LOWER(CONCAT('%', :genre, '%')) OR LOWER(genre.genre_vie) LIKE LOWER(CONCAT('%', :genre, '%'))) AND movie.type LIKE LOWER(CONCAT('%', :type, '%')) AND view.user_id = :user_id", nativeQuery = true)
    Page<Movie> getWatchedByUser(@Param("genre") String genre, @Param("type") String type, Pageable pageable, @Param("user_id") int user_id);

    @Query(value = "SELECT DISTINCT movie.* FROM movie INNER JOIN movie_genre on movie_genre.movie_id = movie.movie_id INNER JOIN genre on genre.genre_id = movie_genre.genre_id WHERE movie.name LIKE LOWER(CONCAT('%', :movieName, '%')) AND genre.genre LIKE LOWER(CONCAT('%', :genres, '%')) AND movie.rated BETWEEN :rated AND :ratedEnd AND movie.year BETWEEN :releaseStart AND :releaseEnd", nativeQuery = true)
    Page<Movie> searchMovieByFormSearchWithGenres(
            @Param("movieName") String movieName,
            @Param("genres") String genres,
            @Param("rated") int rated,
            @Param("ratedEnd") int ratedEnd,
            @Param("releaseStart") int releaseStart,
            @Param("releaseEnd") int releaseEnd,
            Pageable pageable
    );

    @Query(value = "SELECT movie.* FROM movie WHERE movie.name LIKE LOWER(CONCAT('%', :movieName, '%')) AND movie.rated BETWEEN :rated AND :ratedEnd AND movie.year BETWEEN :releaseStart AND :releaseEnd", nativeQuery = true)
    Page<Movie> searchMovieByFormSearch(
            @Param("movieName") String movieName,
            @Param("rated") int rated,
            @Param("ratedEnd") int ratedEnd,
            @Param("releaseStart") int releaseStart,
            @Param("releaseEnd") int releaseEnd,
            Pageable pageable
    );

    @Query(value = "SELECT movie.* FROM movie ORDER BY movie.rated DESC LIMIT 10", nativeQuery = true)
    List<Movie> getMoviesByRated();

//    update favorite
    @Query(value = "SELECT EXISTS(SELECT 1 FROM favorite WHERE movie_id = :movie_id AND user_id = :user_id);", nativeQuery = true)
    Integer checkExistFavorite(@Param("movie_id") int movie_id, @Param("user_id") int user_id);
    @Modifying
    @Query(value = "INSERT INTO favorite VALUE (:user_id, :movie_id)", nativeQuery = true)
    void insertFavorite(@Param("movie_id") int movie_id, @Param("user_id") int user_id);
    @Modifying
    @Query(value = "DELETE FROM favorite WHERE movie_id = :movie_id AND user_id = :user_id", nativeQuery = true)
    void deleteFavorite(@Param("movie_id") int movie_id, @Param("user_id") int user_id);
//  update lượt xem
    @Modifying
    @Query(value = "UPDATE movie SET movie.view = movie.view + 1 WHERE movie.movie_id = :movie_id", nativeQuery = true)
    void updateViewMovie(@Param("movie_id") int movie_id);
    @Modifying
    @Query(value = "INSERT INTO view (movie_id, user_id, view) VALUES (:movie_id, :user_id, 1) ON DUPLICATE KEY UPDATE view.view = view.view + 1;", nativeQuery = true)
    void updateViewUser(@Param("movie_id") int movie_id, @Param("user_id") int user_id);


}
