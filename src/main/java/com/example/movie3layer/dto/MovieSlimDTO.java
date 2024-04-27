package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieSlimDTO {
    @JsonProperty("movie_id")
    private int movie_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("view")
    private String view;
    @JsonProperty("content")
    private String content;
    @JsonProperty("year")
    private String year;
    @JsonProperty("rated")
    private double rated;
    @JsonProperty("runtime")
    private String runtime;
    @JsonProperty("type")
    private String type;
    @JsonProperty("genres")
    private List<String> genres;
    @JsonProperty("language")
    private String language;
    @JsonProperty("poster")
    private String poster;
    @JsonProperty("thumb")
    private String thumb;
    @JsonProperty("episodes")
    private List<EpisodesDTO> episodes;
}
