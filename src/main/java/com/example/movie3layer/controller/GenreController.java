package com.example.movie3layer.controller;

import com.example.movie3layer.dto.GenreDTO;
import com.example.movie3layer.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {
    @Autowired
    private GenreService genreService;
    @GetMapping("/all")
    public ResponseEntity<?> getAllGenre() {
        return new ResponseEntity<>(
                genreService.getAllGenre(),
                HttpStatus.OK
        );
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getGenreByID(
            @RequestParam("id") int id
    ) {
        return new ResponseEntity<>(
                genreService.getGenreByID(id),
                HttpStatus.OK
        );
    }
    @GetMapping("/movie")
    public ResponseEntity<?> getGenreMovieID(
            @RequestParam String movie_id
    ) {
        return new ResponseEntity<>(
                "genreService.getGenreByMovieID(movie_id)",
                HttpStatus.OK
        );
    }
    @PostMapping("/add/genre/admin")
    public ResponseEntity<?> adminSaveMovie(
            @RequestBody GenreDTO genreDTO
    ) throws IOException {

        try {
            Boolean aBoolean = genreService.saveMovieAdmin(genreDTO);
            if (aBoolean) {
                return new ResponseEntity<>("Thêm mới thành công", HttpStatus.OK);

            } else {
                return new ResponseEntity<>("Thêm mới thất bại", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/delete/genre/admin")
    public ResponseEntity<?> deleteMovie(
            @RequestParam("id") String id
    ) throws IOException {

        try {
            genreService.removeGenre(id);
            return new ResponseEntity<>(
                    "Xóa thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Xóa không thành công: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
