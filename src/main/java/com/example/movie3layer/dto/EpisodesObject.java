package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodesObject {
    @JsonProperty("server_name")
    private String serverName;
    @JsonProperty("server_data")
    private List<EpisodesDTO> episodesList;
}
