package com.example.movie3layer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieID;
    @Column(name = "name")
    private String name; // tên movie
    @Column(name = "slug")
    private String slug;
    @Column(name = "year")
    private String year; // năm
    @Column(name = "endYear")
    private String endYear; // năm
    @Column(name = "rated")
    private double rated; // số điểm đánh giá
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "director_id")
    )
    private List<Director> directorList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    )
    private List<Actor> actorList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "movie_writer",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id", referencedColumnName = "writer_id")
    )
    private List<Writer> writerList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    )
    private List<Genre> genreList;
    @Column(name = "type")
    private String type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "movie_episodes",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "episodes_id", referencedColumnName = "episodes_id")
    )
    private List<Episodes> episodes;


    @ManyToMany(mappedBy = "favorites", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<User> users;

    @ManyToMany(mappedBy = "view", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<User> user_view;

//    @ManyToMany(mappedBy = "evaluates", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    private List<User> evaluates;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<Comment> comments;

    @Column(name = "released")
    private String released; // ngày ra mắt
    @Column(name = "runtime")
    private String runtime; // thời lượng phim
    @Column(name = "language")
    private String language; // ngôn ngữ
    @Column(name = "country")
    private String country; // quốc gia
    @Column(name = "awards")
    private String awards; // giải thưởng
    @Column(name = "content", columnDefinition = "TEXT")
    private String content; // mô tả
    @Column(name = "poster")
    private String poster; // link poster phim
    @Column(name = "thumb")
    private String thumb; // link poster phim
    @Column(name = "trailer")
    private String trailer; // link poster phim
    @Column(name = "view", columnDefinition = "INT DEFAULT 0")
    private int view; // link poster phim

    public Movie(
            String name, String slug, String year, String endYear, double rated,
            List<Actor> actors,
            List<Genre> genres,
            String type,
            List<Episodes> episodes,
            String released,
            String runtime, String language, String country, String awards, String content,
            String poster, String thumb, String trailer) {
        this.name = name;
        this.slug = slug;
        this.year = year;
        this.endYear = endYear;
        this.rated = rated;
        this.actorList = actors;
        this.genreList = genres;
        this.type = type;
        this.episodes = episodes;
        this.released = released;
        this.runtime = runtime;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.content = content;
        this.poster = poster;
        this.thumb = thumb;
        this.trailer = trailer;
    }
    public Movie(
            String name, String slug, String year, String endYear, double rated,
//            List<Genre> genres,
            String type,
            List<Episodes> episodes,
            String released,
            String runtime, String language, String country, String awards, String content,
            String poster, String thumb, String trailer) {
        this.name = name;
        this.slug = slug;
        this.year = year;
        this.endYear = endYear;
        this.rated = rated;
//        this.genreList = genres;
        this.type = type;
        this.episodes = episodes;
        this.released = released;
        this.runtime = runtime;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.content = content;
        this.poster = poster;
        this.thumb = thumb;
        this.trailer = trailer;
    }
    public Movie(
            String name, String slug, String year, String endYear, double rated,
            List<Actor> actors,
            List<Genre> genres,
            String type,
            List<Episodes> episodes,
            String released,
            String runtime, String language, String country, String awards, String content,
            String poster, String thumb, String trailer, int view) {
        this.name = name;
        this.slug = slug;
        this.year = year;
        this.endYear = endYear;
        this.rated = rated;
        this.actorList = actors;
        this.genreList = genres;
        this.type = type;
        this.episodes = episodes;
        this.released = released;
        this.runtime = runtime;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.content = content;
        this.poster = poster;
        this.thumb = thumb;
        this.trailer = trailer;
    }

    public Movie(
            int movieID,
            String name, String slug, String year, String endYear, double rated,
            List<Actor> actors,
            List<Genre> genres,
            String type,
            List<Episodes> episodes,
            String released,
            String runtime, String language, String country, String awards, String content,
            String poster, String thumb, String trailer, int view) {
        this.movieID = movieID;
        this.name = name;
        this.slug = slug;
        this.year = year;
        this.endYear = endYear;
        this.rated = rated;
        this.actorList = actors;
        this.genreList = genres;
        this.type = type;
        this.episodes = episodes;
        this.released = released;
        this.runtime = runtime;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.content = content;
        this.poster = poster;
        this.thumb = thumb;
        this.trailer = trailer;
        this.view = view;
    }
}
