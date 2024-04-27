package com.example.movie3layer.controller;

import com.example.movie3layer.response_modal.EvaluateRequest;
import com.example.movie3layer.service.EvaluateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluate")
@RequiredArgsConstructor
public class EvaluateController {
    @Autowired
    EvaluateService evaluateService;

    @PostMapping("/add")
    public ResponseEntity<?> addComment (
            @RequestBody EvaluateRequest evaluateRequest
    ) {
        try {
            evaluateService.updateEvaluate(evaluateRequest);
            return new ResponseEntity<>(
                    "Thêm thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Thêm thất bại",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
    @PostMapping("/remove")
    public ResponseEntity<?> removeComment (
            @RequestParam int comment_id
    ) {
        try {
            evaluateService.removeComment(comment_id);
            return new ResponseEntity<>(
                    "Xóa thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Xóa thất bại",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
