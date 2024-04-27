package com.example.movie3layer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int GenreID;

    @Column(name = "genre")
    private String Genre; // tên thể loại

    @Column(name = "genre_vie")
    private String GenreVie; // tên thể loại

    @ManyToMany(mappedBy = "genreList", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Movie> movieList;

}
