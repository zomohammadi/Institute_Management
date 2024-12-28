package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.dto.ResponseCourseDto;
import org.example.institutemanagement.dto.projection.ResponseCourseProjection;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.entity.Lesson;
import org.example.institutemanagement.entity.Teacher;
import org.example.institutemanagement.entity.Term;
import org.example.institutemanagement.enumaration.Day;
import org.example.institutemanagement.exception.NotFoundException;
import org.example.institutemanagement.mapper.Mapper;
import org.example.institutemanagement.repository.CourseRepository;
import org.example.institutemanagement.repository.LessonRepository;
import org.example.institutemanagement.repository.TeacherRepository;
import org.example.institutemanagement.repository.TermRepository;
import org.example.institutemanagement.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final TermRepository termRepository;
    private final LessonRepository lessonRepository;


    @Override
    @Transactional
    public void save(RegisterCourseDto dto) {

        if (!teacherRepository.existsById(dto.teacherId()))
            throw new NotFoundException("Teacher not found");

        if (!termRepository.existsById(dto.termId()))
            throw new NotFoundException("Term not found");

        if (!lessonRepository.existsById(dto.lessonId()))
            throw new NotFoundException("Lesson not found");

        checkCourseTimeConflict(dto.startHour(), dto.endHour(), dto.day(), dto.termId(), dto.teacherId());


        Course course = Course.builder().capacity(dto.capacity()).startHour(dto.startHour())
                .endHour(dto.endHour())
                .day(Day.valueOf(dto.day()))
                .teacher(Teacher.builder().id(dto.teacherId()).build())
                .term(Term.builder().id(dto.termId()).build())
                .lesson(Lesson.builder().id(dto.lessonId()).build())
                .build();

        courseRepository.save(course);
    }

    private void checkCourseTimeConflict(Integer startHour, Integer endHour, String day, Long termId, Long teacherId) {
        Day dayEnum = Day.valueOf(day);
        List<Course> conflictingCourses = courseRepository.findConflictingCourses(startHour, endHour, dayEnum, termId, teacherId);

        if (!conflictingCourses.isEmpty()) {
            throw new RuntimeException("Time conflict detected with existing courses");
        }
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
