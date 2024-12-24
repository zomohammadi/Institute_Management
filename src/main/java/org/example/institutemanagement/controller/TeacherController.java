package org.example.institutemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterStudentDto;
import org.example.institutemanagement.dto.RegisterTeacherDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.service.StudentService;
import org.example.institutemanagement.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @PostMapping("/TeacherRegister")
    public ResponseEntity<ResponsePersonDto> teacherRegister(@RequestBody @Valid RegisterTeacherDto dto) {
        return new ResponseEntity<>(teacherService.save(dto), HttpStatus.CREATED);
    }
}
