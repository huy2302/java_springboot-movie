package com.example.movie3layer.service;

import com.example.movie3layer.response_modal.EvaluateRequest;

public interface EvaluateService {
    void updateEvaluate (EvaluateRequest evaluateRequest);
    void removeComment(int commentId);
}
