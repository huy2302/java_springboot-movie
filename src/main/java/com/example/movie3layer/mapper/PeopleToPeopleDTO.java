package com.example.movie3layer.mapper;

import com.example.movie3layer.dto.PeopleDTO;
import com.example.movie3layer.model.Actor;
import org.springframework.stereotype.Service;

@Service
public class PeopleToPeopleDTO {
    public PeopleDTO ActorToPersonDTO(Actor actor) {
        return new PeopleDTO(
                actor.getActorID(),
                actor.getActorName(),
                actor.getAge(),
                actor.getCountry(),
                actor.getGender(),
                actor.getAvt(),
                actor.getDescription()
        );
    }
//    public PersonDTO DirectorToPersonDTO(Director director) {
//        return new PersonDTO(
//                director.getDirectorID(),
//                director.getDirectorName(),
//                director.getAge(),
//                director.getCountry(),
//                director.getGender(),
//                director.getAvt(),
//                director.getDescription()
//        );
//    }
//    public PersonDTO WriterToPersonDTO(Writer writer) {
//        return new PersonDTO(
//                writer.getWriterID(),
//                writer.getWriterName(),
//                writer.getAge(),
//                writer.getCountry(),
//                writer.getGender(),
//                writer.getAvt(),
//                writer.getDescription()
//        );
//    }
}
