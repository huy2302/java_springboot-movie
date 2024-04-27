package com.example.movie3layer.repository;

import com.example.movie3layer.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    lấy ra comment của movie với movie_id
    @Query("select c from Comment c where c.movie.movieID = :id order by c.time desc")
    List<Comment> getCommentByMovieID(int id);

//    lấy ra comment của movie với user_id
    @Query("select c from Comment c where c.user.userID = :id ")
    List<Comment> getCommentByUserID(int id);

//    thêm comment
    @Modifying
    @Query(value = "INSERT INTO comments (movie_id, user_id, tag_id, time, comment) VALUES (:movie_id, :user_id, :tag_id, now(), :comment);", nativeQuery = true)
    void addComment(@Param("movie_id") int movie_id, @Param("user_id") int user_id, @Param("tag_id") int tag_id, @Param("comment") String comment);

    //    xóa comment
    @Modifying
    @Query(value = "DELETE FROM comments WHERE comment_id = :comment_id", nativeQuery = true)
    void removeComment(@Param("comment_id") int comment_id);

}
