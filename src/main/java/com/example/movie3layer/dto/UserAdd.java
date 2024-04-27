package com.example.movie3layer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAdd {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phone_number;

    @JsonProperty("role")
    private String role;

    @JsonProperty("password")
    private String password;

    @JsonProperty("avatar")
    private String avatar;
}
