package com.example.movie3layer.mapper;

import com.example.movie3layer.dto.EvaluateDTO;
import com.example.movie3layer.dto.UserShortDTO;
import com.example.movie3layer.model.Evaluate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EvaluateToEvaluateDTO implements Function<Evaluate, EvaluateDTO> {

    @Override
    public EvaluateDTO apply(Evaluate evaluate) {
        return new EvaluateDTO(
                evaluate.getEvaluateID(),
                evaluate.getMovie().getMovieID(),
                new UserShortDTO(
                        evaluate.getUser().getUserID(),
                        evaluate.getUser().getUsername(),
                        evaluate.getUser().getAvatar()
                ),
                evaluate.getEvaluate()
        );
    }
}
