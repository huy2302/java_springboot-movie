package com.example.movie3layer.service;

import com.example.movie3layer.dto.PeopleDTO;
import com.example.movie3layer.exception.NotFoundException;
import com.example.movie3layer.mapper.PeopleToPeopleDTO;
import com.example.movie3layer.model.Actor;
import com.example.movie3layer.model.MapStringObject;
import com.example.movie3layer.model.MetaData;
import com.example.movie3layer.model.Movie;
import com.example.movie3layer.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService{
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private PeopleToPeopleDTO peopleToPeopleDTO;

    private MetaData metaData;
    private MapStringObject objectMap;

//    Actor service
    @Override
    public MapStringObject getListActor(String name, int page) {
        Page<Actor> moviePage;
        Pageable pageable = PageRequest.of(page, 100);

//        cần set name default trong controller là ""
        if (name.equals("")) {
            moviePage = actorRepository.findAll(pageable);
            if (moviePage.getContent().size() > 0) {
                metaData = new MetaData(
                        moviePage.getTotalElements(),
                        pageable.getPageNumber() + 1,
                        moviePage.getTotalPages()
                );
                objectMap = new MapStringObject(
                        moviePage.stream().map(peopleToPeopleDTO::ActorToPersonDTO).collect(Collectors.toList()),
                        metaData
                );
                return objectMap;
            }
            throw new NotFoundException("Không tồn tại actor nào");
        } else { // name != "" -> là search
            moviePage = actorRepository.getActorByActorName(name, pageable);
            if (moviePage.getContent().size() > 0) {
                metaData = new MetaData(
                        moviePage.getTotalElements(),
                        pageable.getPageNumber() + 1,
                        moviePage.getTotalPages()
                );
                objectMap = new MapStringObject(
                        moviePage.stream().map(peopleToPeopleDTO::ActorToPersonDTO).collect(Collectors.toList()),
                        metaData
                );
                return objectMap;
            }
            throw new NotFoundException("Không tồn tại actor nào");
        }
    }

    @Override
    public PeopleDTO getActorByActorName(String id) {
        if (isInteger(id)) {
            Actor actor = actorRepository.findActorByActorID(Integer.parseInt(id));
//            Actor actor = null;
            if (actor != null) {
                return peopleToPeopleDTO.ActorToPersonDTO(actor);
            }
            throw new NotFoundException("Không tồn tại actor với id " + id);
        }
        throw new NotFoundException("ID actor được yêu cầu là int");
    }

    @Override
    public MapStringObject getListActorByMovieID(String id, int page) {
        Pageable pageable = PageRequest.of(page, 100);
        if (isInteger(id)) {
            Page<Actor> actorList = actorRepository.getActorByMovieID(Integer.parseInt(id), pageable);
//            Actor actor = null;
            if (actorList.getContent().size() > 0) {
                metaData = new MetaData(
                        actorList.getTotalElements(),
                        pageable.getPageNumber() + 1,
                        actorList.getTotalPages()
                );
                objectMap = new MapStringObject(
                        actorList.stream().map(peopleToPeopleDTO::ActorToPersonDTO).collect(Collectors.toList()),
                        metaData
                );
                return objectMap;
            }
            throw new NotFoundException("Không tồn tại actor");
        }
        throw new NotFoundException("ID actor được yêu cầu là int");
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
