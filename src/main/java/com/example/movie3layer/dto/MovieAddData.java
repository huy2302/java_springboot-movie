package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieAddData {
    @JsonProperty("movie")
    public MovieAdd movie;

    @JsonProperty("episodes")
    public List<EpisodesObject> episodes;
}