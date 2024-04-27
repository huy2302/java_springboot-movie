package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    @JsonProperty("movie_id")
    private int movie_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("view")
    private String view;
    @JsonProperty("year")
    private String year;
    @JsonProperty("endYear")
    private String endYear;
    @JsonProperty("rated")
    private double rated;
    @JsonProperty("released")
    private String released;
    @JsonProperty("runtime")
    private String runtime;
    @JsonProperty("type")
    private String type;
    @JsonProperty("genres")
    private List<String> genres;
    @JsonProperty("directors")
    private List<String> directors;
    @JsonProperty("actors")
    private List<String> actors;
    @JsonProperty("content")
    private String content;
    @JsonProperty("country")
    private String country;
    @JsonProperty("awards")
    private String awards;
    @JsonProperty("language")
    private String language;
    @JsonProperty("poster")
    private String poster;
    @JsonProperty("thumb")
    private String thumb;
    @JsonProperty("trailer")
    private String trailer;
    @JsonProperty("episodes")
    private List<EpisodesDTO> episodes;
//    @JsonProperty("comments")
//    private List<CommentDTO> comments;
}
