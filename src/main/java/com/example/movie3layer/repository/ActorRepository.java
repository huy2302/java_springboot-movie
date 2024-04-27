package com.example.movie3layer.repository;

import com.example.movie3layer.model.Actor;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Query(
            value = "SELECT DISTINCT actor.* FROM actor " +
            "WHERE LOWER(actor.actor_name) LIKE LOWER(CONCAT('%', :name , '%'))",
            nativeQuery = true
    )
    Page<Actor> getActorByActorName(@Param("name") String name, Pageable pageable);

    @Query(
            value = "SELECT actor.* FROM actor WHERE actor.actor_id = :id",
            nativeQuery = true
    )
    Actor findActorByActorID(int id);

    @Query(
            value = "SELECT DISTINCT actor.* FROM actor " +
                    "JOIN movie_actor ON movie_actor.actor_id = actor.actor_id " +
                    "WHERE movie_actor.movie_id = :movie_id",
            nativeQuery = true
    )
    Page<Actor> getActorByMovieID(@Param("movie_id") int movie_id, Pageable pageable);

    @Query(
            value = "SELECT actor.* FROM actor WHERE actor.actor_name = :actor_name",
            nativeQuery = true
    )
    Actor getActorByName(@Param("actor_name") String actor_name);
}
