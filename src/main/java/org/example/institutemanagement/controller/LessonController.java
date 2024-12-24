package org.example.institutemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterLessonDto;
import org.example.institutemanagement.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/LessonRegister")
    public ResponseEntity<Void> studentRegister(@RequestBody @Valid RegisterLessonDto dto) {

        lessonService.save(dto);
        return new ResponseEntity<>( HttpStatus.CREATED);

    }
}
