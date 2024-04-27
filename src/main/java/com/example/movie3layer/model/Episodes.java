package com.example.movie3layer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Episodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episodes_id")
    private int episodes_id;

    @ManyToMany(mappedBy = "episodes", fetch = FetchType.LAZY)
    private List<Movie> movieList;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug; // tên movie

    @Column(name = "file_name")
    private String file_name;

    @Column(name = "link")
    private String link; // năm

    public Episodes(String name, String slug, String file_name, String link) {
        this.name = name;
        this.slug = slug;
        this.file_name = file_name;
        this.link = link;
    }
    public Episodes(int id, String name, String slug, String file_name, String link) {
        this.episodes_id = id;
        this.name = name;
        this.slug = slug;
        this.file_name = file_name;
        this.link = link;
    }
}
