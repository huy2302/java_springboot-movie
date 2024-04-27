package com.example.movie3layer.controller;

import com.example.movie3layer.dto.CommentDTO;
import com.example.movie3layer.response_modal.CommentRequest;
import com.example.movie3layer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    private CommentService commentService;

    //    lấy ra comment bằng movie_id
    @GetMapping("/movie")
    public ResponseEntity<?> getCommentsByMovieID (
            @RequestParam("id") int id
    ) {
        List<CommentDTO> movieDTO = commentService.getCommentsByMovieID(id);

        return new ResponseEntity<>(
                movieDTO,
                HttpStatus.OK
        );
    }
    @GetMapping("/user")
    public ResponseEntity<?> getCommentsByUserID (
            @RequestParam("id") int id
    ) {
        List<CommentDTO> movieDTO = commentService.getCommentsByUserID(id);

        return new ResponseEntity<>(
                movieDTO,
                HttpStatus.OK
        );
    }
    @PostMapping("/add")
    public ResponseEntity<?> addComment (
            @RequestBody CommentRequest commentResponse
            ) {
        try {
            commentService.addComment(commentResponse);
            return new ResponseEntity<>(
                    "Thêm thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Thêm thất bại",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
    @PostMapping("/remove")
    public ResponseEntity<?> removeComment (
            @RequestParam int comment_id
    ) {
        try {
            commentService.removeComment(comment_id);
            return new ResponseEntity<>(
                    "Xóa thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Xóa thất bại",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
