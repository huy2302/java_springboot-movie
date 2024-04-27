package com.example.movie3layer.service;

import com.example.movie3layer.dto.CommentDTO;
import com.example.movie3layer.response_modal.CommentRequest;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getCommentsByMovieID (int movie_id);
    List<CommentDTO> getCommentsByUserID (int user_id);
    void addComment (CommentRequest commentRequest);
    void removeComment (int comment_id);
    Boolean addComment (int movie_id, int user_id, int tag_id, String comment);

}
