package com.example.movie3layer.mapper;

import com.example.movie3layer.dto.CommentDTO;
import com.example.movie3layer.dto.UserShortDTO;
import com.example.movie3layer.model.Comment;
import com.example.movie3layer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CommentToCommentDTO implements Function<Comment, CommentDTO> {
    @Autowired
    UserService userService;
    @Override
    public CommentDTO apply(Comment comment) {
        return new CommentDTO(
                comment.getCommentID(),
                comment.getMovie().getMovieID(),
                new UserShortDTO(
                        comment.getUser().getUserID(),
                        comment.getUser().getUsername(),
                        comment.getUser().getAvatar()
                ),
                comment.getComment(),
                comment.getTagID(),
                comment.getTime()
        );
    }
}
