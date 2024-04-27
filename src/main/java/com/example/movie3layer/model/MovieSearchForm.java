package com.example.movie3layer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchForm {
    private String movieName;
    private String genres;
    private int ratingRange;
    private int releaseYearStart;
    private int releaseYearEnd;
}
