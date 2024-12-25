package org.example.institutemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.EvaluationDto;
import org.example.institutemanagement.service.EvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/GradingStudent")
    public ResponseEntity<Void> gradingStudent(@RequestBody @Valid EvaluationDto dto) {

        evaluationService.gradingStudent(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
