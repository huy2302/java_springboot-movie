package com.example.movie3layer.controller;

import com.example.movie3layer.dto.MovieAddData;
import com.example.movie3layer.dto.MovieDTO;
import com.example.movie3layer.model.Images;
import com.example.movie3layer.model.MapStringObject;
import com.example.movie3layer.model.Movie;
import com.example.movie3layer.model.MovieSearchForm;
import com.example.movie3layer.service.CommentService;
import com.example.movie3layer.service.ImagesService;
import com.example.movie3layer.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//@CrossOrigin("http://127.0.0.1:5173")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllMovies (
            @RequestParam(name = "genre", required = false, defaultValue = "") String genre,
            @RequestParam(name = "type", required = false, defaultValue = "") String type,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page
    ) {

        MapStringObject movieDTOs = movieService.getAllMovie(genre, type, page);

        return new ResponseEntity<>(movieDTOs, HttpStatus.OK);
    }
    @GetMapping("/advanced")
    public ResponseEntity<?> getMovieAdvanced (
            @RequestParam(name = "choice", required = false, defaultValue = "") String choice,
            @RequestParam(name = "type", required = false, defaultValue = "") String type,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page
    ) {

        MapStringObject movieDTOs = movieService.getMovieByChoice(choice, type, page);

         return new ResponseEntity<>(movieDTOs, HttpStatus.OK);
    }
    @GetMapping("/all/user")
    public ResponseEntity<?> getAllMovieByUser (
            @RequestParam(name = "genre", required = false, defaultValue = "") String genre,
            @RequestParam(name = "type", required = false, defaultValue = "") String type,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "user_id", required = false, defaultValue = "0") int user_id
    ) {

        MapStringObject movieDTOs = movieService.getAllFavoriteByUser(genre, type, page, user_id);

        return new ResponseEntity<>(movieDTOs, HttpStatus.OK);
    }
    @GetMapping("/watched/user")
    public ResponseEntity<?> getAllWatchedByUser (
            @RequestParam(name = "genre", required = false, defaultValue = "") String genre,
            @RequestParam(name = "type", required = false, defaultValue = "") String type,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "user_id", required = false, defaultValue = "0") int user_id
    ) {

        MapStringObject movieDTOs = movieService.getAllWatchedByUser(genre, type, page, user_id);

        return new ResponseEntity<>(movieDTOs, HttpStatus.OK);
    }
    @GetMapping("/details")
    public ResponseEntity<?> findMovieByID (
            @RequestParam(name = "id", required = true) int id
    ) {
        MovieDTO movieDTO = movieService.getMovieByID(id);

        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }
    @GetMapping("/movie-trailer-limit")
    public ResponseEntity<?> getMoviesLimit () {
        List<MovieDTO> movieDTOS = movieService.getMoviesByRated();

        return new ResponseEntity<>(movieDTOS, HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<?> searchMovie (
            @RequestBody MovieSearchForm movieSearchForm
            ) {
        MapStringObject object = movieService.searchMoviesWithForm(movieSearchForm);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
    @GetMapping("/favorite")
    public ResponseEntity<?> changeFavorite (
            @RequestParam String movie_id,
            @RequestParam String user_id
    ) {
        Boolean aBoolean = movieService.updateFavorite(movie_id, user_id);

        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
    }
    @GetMapping("/view")
    public ResponseEntity<?> updateView (
            @RequestParam int movie_id,
            @RequestParam int user_id
    ) {
        Boolean aBoolean = movieService.updateView(movie_id, user_id);

        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
    }
    @GetMapping("/favorite-user")
    public ResponseEntity<?> checkFavoriteUser (
            @RequestParam String movie_id,
            @RequestParam String user_id
    ) {
        Boolean aBoolean = movieService.checkFavorite(movie_id, user_id);

        return new ResponseEntity<>(aBoolean, HttpStatus.OK);
    }
    @PostMapping("/add/movie")
    public ResponseEntity<?> saveMovie(
            @RequestBody MovieAddData movieDTO
            ) throws IOException {

        try {
            Movie movie = movieService.saveMovie(movieDTO);

            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/add/movie/admin")
    public ResponseEntity<?> adminSaveMovie(
            @RequestBody MovieDTO movieDTO
            ) throws IOException {

        try {
            Movie movie = movieService.saveMovieAdmin(movieDTO);

            return new ResponseEntity<>("Thêm mới thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/delete/movie/admin")
    public ResponseEntity<?> deleteMovie(
            @RequestParam("movie_id") String movie_id
    ) throws IOException {

        try {
            movieService.removeMovie(movie_id);
            return new ResponseEntity<>(
                    "Xóa thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Xóa không thành công: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/save-image")
    public ResponseEntity<?> saveImages (
            @RequestPart("image_data") MultipartFile image,
            @RequestPart("image_name") String imageName
            ) throws IOException {

        try {
            Images savedImage = imagesService.SaveImages(image, imageName);
            return new ResponseEntity<>(savedImage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
