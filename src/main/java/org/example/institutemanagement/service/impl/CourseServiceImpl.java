package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.dto.ResponseCourseDto;
import org.example.institutemanagement.dto.projection.ResponseCourseProjection;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.exception.NotFoundException;
import org.example.institutemanagement.mapper.Mapper;
import org.example.institutemanagement.repository.CourseRepository;
import org.example.institutemanagement.service.CourseService;
import org.example.institutemanagement.validation.CourseValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseValidator courseValidator;


    @Override
    @Transactional
    public void save(RegisterCourseDto dto) {

        courseValidator.validateCourseData(dto);
        Course course = Mapper.convertDtoToCourse(dto);

        courseRepository.save(course);
    }


    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
    }

    @Override
    public List<ResponseCourseDto> findCourses(Long teacherId, Long termID) {

        List<ResponseCourseProjection> proj = courseRepository.findCourses(teacherId, termID);

        return Mapper.convertCourseProjectionListToResponseCourseDto(proj);


    }
}
