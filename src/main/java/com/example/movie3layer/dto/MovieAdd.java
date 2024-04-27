package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieAdd {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("origin_name")
    private String originName;
    @JsonProperty("content")
    private String content;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("poster_url")
    private String posterUrl;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    @JsonProperty("isCopyright")
    private boolean isCopyright;
    @JsonProperty("subDocquyen")
    private boolean subDocquyen;
    @JsonProperty("chieurap")
    private boolean chieurap;
    @JsonProperty("trailer_url")
    private String trailerUrl;
    @JsonProperty("time")
    private String time;
    @JsonProperty("episodeCurrent")
    private String episodeCurrent;
    @JsonProperty("episodeTotal")
    private String episodeTotal;
    @JsonProperty("quality")
    private String quality;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("notify")
    private String notify;
    @JsonProperty("showtimes")
    private String showtimes;
    @JsonProperty("year")
    private int year;
    @JsonProperty("view")
    private int view;
    @JsonProperty("category")
    private List<Object> genreList;
    @JsonProperty("director")
    private List<Object> director;
    @JsonProperty("actor")
    private List<Object> actor;

}
