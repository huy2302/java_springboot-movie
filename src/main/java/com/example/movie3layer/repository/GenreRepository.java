package com.example.movie3layer.repository;

import com.example.movie3layer.model.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query(value = "SELECT genre.* FROM genre WHERE genre.genre_vie = :genre_vie", nativeQuery = true)
    Genre getGenreByGenreNameVie(@Param("genre_vie") String genre_vie);
    @Query(value = "SELECT genre.* FROM genre WHERE genre.genre_id = :genre_id", nativeQuery = true)
    Genre getGenreByID(@Param("genre_id") int genre_id);
    @Modifying
    @Query(value = "INSERT INTO `movie`.`genre` (`genre`, `genre_vie`) VALUES (:genre, :genre_vie);", nativeQuery = true)
    void addGenre(@Param("genre") String genre, @Param("genre_vie") String genre_vie);
    @Modifying
    @Query(value = "DELETE FROM `movie`.`movie_genre` WHERE (`genre_id` = :genre_id); ", nativeQuery = true)
    void deleteMovieGenre(@Param("genre_id") int genre_id);
}
