package com.example.movie3layer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapStringObject {
    @JsonProperty("data")
    private Object data;
    @JsonProperty("meta_data")
    private MetaData metaData;

}
