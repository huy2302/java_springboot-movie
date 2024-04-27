package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    @JsonProperty("comment_id")
    private int comment_id;

    @JsonProperty("movie_id")
    private int movie_id;

    @JsonProperty("user")
    private UserShortDTO user;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("tag_id")
    private int tagID;

    @JsonProperty("time")
    private Date time;
}
