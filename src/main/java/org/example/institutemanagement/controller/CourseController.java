package org.example.institutemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.dto.RequestCourseDto;
import org.example.institutemanagement.dto.ResponseCourseDto;
import org.example.institutemanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/CourseRegister")
    public ResponseEntity<Void> courseRegister(@RequestBody @Valid RegisterCourseDto dto) {

        courseService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/findCourses")
    public ResponseEntity<List<ResponseCourseDto>> findCourses
            (@RequestBody @Valid RequestCourseDto dto){

        List<ResponseCourseDto> courses = courseService.findCourses
                (dto.teacherId(), dto.termId());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
