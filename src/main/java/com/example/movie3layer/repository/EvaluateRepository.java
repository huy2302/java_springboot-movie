package com.example.movie3layer.repository;

import com.example.movie3layer.model.Evaluate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EvaluateRepository extends JpaRepository<Evaluate, Integer> {
//    thêm đánh giá
    @Modifying
    @Query(value = "INSERT INTO evaluates (movie_id, user_id, time, evaluate) SELECT :movie_id, :user_id, now(), :evaluate FROM dual WHERE NOT EXISTS (SELECT * FROM evaluates WHERE movie_id = :movie_id AND user_id = :user_id ); ", nativeQuery = true)
    void addEvaluate(@Param("movie_id") int movie_id, @Param("user_id") int user_id, @Param("evaluate") int evaluate);

//    chỉnh sửa rated trong bảng movie
    @Modifying
    @Query(value = "UPDATE `movie`.`movie` SET `rated` = (SELECT AVG(evaluate) AS average_evaluate FROM evaluates WHERE movie_id = :movie_id) WHERE (`movie_id` = :movie_id);", nativeQuery = true)
    void updateRated(@Param("movie_id") int movie_id);

    @Modifying
    @Query(value = "DELETE FROM evaluates WHERE evaluate_id = :evaluate_id", nativeQuery = true)
    void removeComment(@Param("evaluate_id") int evaluate_id);
}
