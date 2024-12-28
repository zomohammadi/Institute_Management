package org.example.institutemanagement.validation;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.enumaration.Day;
import org.example.institutemanagement.exception.NotFoundException;
import org.example.institutemanagement.repository.CourseRepository;
import org.example.institutemanagement.repository.LessonRepository;
import org.example.institutemanagement.repository.TeacherRepository;
import org.example.institutemanagement.repository.TermRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseValidator {

    private final TeacherRepository teacherRepository;
    private final TermRepository termRepository;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public void validateCourseData(RegisterCourseDto dto) {
        if (!teacherRepository.existsById(dto.teacherId()))
            throw new NotFoundException("Teacher not found");

        if (!termRepository.existsById(dto.termId()))
            throw new NotFoundException("Term not found");

        if (!lessonRepository.existsById(dto.lessonId()))
            throw new NotFoundException("Lesson not found");
        checkCourseTimeConflict(dto.startHour(), dto.endHour(), dto.day(), dto.termId(), dto.teacherId());
    }

    private void checkCourseTimeConflict(Integer startHour, Integer endHour, String day, Long termId, Long teacherId) {
        Day dayEnum = Day.valueOf(day);
        List<Course> conflictingCourses = courseRepository.findConflictingCourses(startHour, endHour, dayEnum, termId, teacherId);

        if (!conflictingCourses.isEmpty()) {
            throw new RuntimeException("Time conflict detected with existing courses");
        }
    }
}
