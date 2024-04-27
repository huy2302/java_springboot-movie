package com.example.movie3layer.service;

import com.example.movie3layer.dto.CommentDTO;
import com.example.movie3layer.mapper.CommentToCommentDTO;
import com.example.movie3layer.model.Comment;
import com.example.movie3layer.repository.CommentRepository;
import com.example.movie3layer.response_modal.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentToCommentDTO commentToCommentDTO;
    @Override
    public List<CommentDTO> getCommentsByMovieID(int movie_id) {
        List<Comment> comments = commentRepository.getCommentByMovieID(movie_id);

        return comments.stream().map(commentToCommentDTO::apply).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByUserID(int user_id) {
        List<Comment> comments = commentRepository.getCommentByUserID(user_id);

        return comments.stream().map(commentToCommentDTO::apply).collect(Collectors.toList());
    }

    @Override
    public void addComment(CommentRequest commentRequest) {
        try {
            commentRepository.addComment(
                    commentRequest.getMovie_id(),
                    commentRequest.getUser_id(),
                    commentRequest.getTag_id(),
                    commentRequest.getComment()
            );
            if (true) {}
        } catch (Exception e) {

        }
    }

    @Override
    public void removeComment(int comment_id) {
        try {
            commentRepository.deleteById(comment_id);

            if (true) {}
        } catch (Exception e) {

        }
    }
    @Override
    public Boolean addComment(int movie_id, int user_id, int tag_id, String comment) {
        try {
            commentRepository.addComment(movie_id, user_id, tag_id, comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
