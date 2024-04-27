package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluateDTO {
    @JsonProperty("evaluate_id")
    private int evaluate_id;

    @JsonProperty("movie_id")
    private int movie_id;

    @JsonProperty("user")
    private UserShortDTO user;

    @JsonProperty("evaluate")
    private int evaluate;
}
