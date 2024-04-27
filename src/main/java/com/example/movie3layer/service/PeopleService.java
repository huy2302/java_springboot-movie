package com.example.movie3layer.service;

import com.example.movie3layer.dto.PeopleDTO;
import com.example.movie3layer.model.Actor;
import com.example.movie3layer.model.MapStringObject;

import java.util.List;

public interface PeopleService {
    MapStringObject getListActor (String name, int page);
    PeopleDTO getActorByActorName(String id);
    MapStringObject getListActorByMovieID (String id, int page);
}
