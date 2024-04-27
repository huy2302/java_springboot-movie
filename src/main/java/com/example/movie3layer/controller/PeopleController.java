package com.example.movie3layer.controller;

import com.example.movie3layer.dto.PeopleDTO;
import com.example.movie3layer.model.MapStringObject;
import com.example.movie3layer.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequestMapping("people")
@RequiredArgsConstructor
public class PeopleController {
    @Autowired
    PeopleService peopleService;

    @GetMapping("/actors")
    public ResponseEntity<?> getAllActors (
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page
    ) {
        MapStringObject listActors = peopleService.getListActor(name, page);
        return new ResponseEntity<>(
                listActors,
                HttpStatus.OK
        );
    }

//    lấy ra actor với actor_id
    @GetMapping("/actor")
    public ResponseEntity<?> getAllActors (
            @RequestParam(name = "id", required = true) String id
    ) {
        PeopleDTO actor = peopleService.getActorByActorName(id);

        return new ResponseEntity<>(
                actor,
                HttpStatus.OK
        );
    }

//    lấy ra các actor ứng với movie_id
    @GetMapping("/movie/actor/{movie_id}")
    public ResponseEntity<?> getAllActorsOfMovie (
            @PathVariable(name = "movie_id") String movie_id,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page
    ) {
        MapStringObject object = peopleService.getListActorByMovieID(movie_id, page);

        return new ResponseEntity<>(
                object,
                HttpStatus.OK
        );
    }
}
