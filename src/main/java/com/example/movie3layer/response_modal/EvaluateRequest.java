package com.example.movie3layer.response_modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateRequest {
    @JsonProperty("movie_id")
    public int movie_id;

    @JsonProperty("user_id")
    public int user_id;

    @JsonProperty("evaluate")
    public int evaluate;
}
