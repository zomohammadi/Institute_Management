package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.dto.ResponseCourseDto;
import org.example.institutemanagement.dto.projection.ResponseCourseProjection;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.exception.NotFoundException;
import org.example.institutemanagement.mapper.CourseMapper;
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
        List<ResponseCourseDto> courses = findCourses(dto.teacherId(), dto.termId());

        for (ResponseCourseDto course : courses) {
            if (dto.day().equalsIgnoreCase(course.day()))
                if (dto.startHour()<course.endHour() && dto.endHour()>course.startHour())
                    throw new RuntimeException("Time conflict detected with existing courses: "+course.lessonName());
        }

        Course course = CourseMapper.convertDtoToCourse(dto);
        courseRepository.save(course);
    }


    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
    }

    @Override
    public List<ResponseCourseDto> findCourses(Long teacherId, Long termID) {

        List<ResponseCourseProjection> proj = courseRepository.findCourses(teacherId, termID);

        return CourseMapper.convertCourseProjectionListToDtoList(proj);


    }
}
