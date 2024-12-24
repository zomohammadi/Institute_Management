package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.entity.Lesson;
import org.example.institutemanagement.entity.Teacher;
import org.example.institutemanagement.entity.Term;
import org.example.institutemanagement.enumaration.Day;
import org.example.institutemanagement.exception.NotFoundException;
import org.example.institutemanagement.repository.CourseRepository;
import org.example.institutemanagement.repository.LessonRepository;
import org.example.institutemanagement.repository.TeacherRepository;
import org.example.institutemanagement.repository.TermRepository;
import org.example.institutemanagement.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Course course = Course.builder().capacity(dto.capacity()).startHour(dto.startHour())
                .endHour(dto.endHour())
                .day(Day.valueOf(dto.day()))
                .teacher(Teacher.builder().id(dto.teacherId()).build())
                .term(Term.builder().id(dto.termId()).build())
                .lesson(Lesson.builder().id(dto.lessonId()).build())
                .build();

        courseRepository.save(course);
    }
}
