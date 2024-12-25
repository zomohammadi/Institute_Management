package org.example.institutemanagement.service;

import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.dto.ResponseCourseDto;
import org.example.institutemanagement.entity.Course;

import java.util.List;

public interface CourseService {

    void save(RegisterCourseDto dto);

    Course findById(Long courseId);

    List<ResponseCourseDto> findCourses(Long teacherId, Long termID);
}
