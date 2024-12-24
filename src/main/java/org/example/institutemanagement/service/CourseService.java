package org.example.institutemanagement.service;

import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.entity.Course;

public interface CourseService {

    void save(RegisterCourseDto dto);

    Course findById(Long courseId);
}
