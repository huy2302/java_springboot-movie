package com.example.movie3layer.service;

import com.example.movie3layer.repository.EvaluateRepository;
import com.example.movie3layer.response_modal.EvaluateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateServiceImpl implements EvaluateService{
    @Autowired
    EvaluateRepository evaluateRepository;
    @Override
    public void updateEvaluate(EvaluateRequest evaluateRequest) {
        try {
            evaluateRepository.addEvaluate(
                    evaluateRequest.getMovie_id(),
                    evaluateRequest.getUser_id(),
                    evaluateRequest.getEvaluate()
            );
            evaluateRepository.updateRated(evaluateRequest.getMovie_id());
        } catch (Exception e) {

        }
    }

    @Override
    public void removeComment(int commentId) {
        try {
            evaluateRepository.removeComment(commentId);

            if(true) {}
        } catch (Exception e) {

        }
    }
}
