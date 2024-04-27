package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {
    @JsonProperty("genre_id")
    private int genre_id;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("genre_vie")
    private String genreVie;
}
