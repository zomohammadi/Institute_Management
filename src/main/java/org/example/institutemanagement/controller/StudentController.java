package org.example.institutemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterStudentDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;



    @PostMapping("/StudentRegister")
    public ResponseEntity<ResponsePersonDto> studentRegister(@RequestBody @Valid RegisterStudentDto dto) {
        return new ResponseEntity<>(studentService.save(dto), HttpStatus.CREATED);
    }
}
