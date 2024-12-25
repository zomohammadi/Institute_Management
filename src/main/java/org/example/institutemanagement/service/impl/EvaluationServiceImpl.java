package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.EvaluationDto;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.entity.Student;
import org.example.institutemanagement.entity.UnitSelection;
import org.example.institutemanagement.exception.NotFoundException;
import org.example.institutemanagement.repository.UnitSelectionRepository;
import org.example.institutemanagement.service.CourseService;
import org.example.institutemanagement.service.EvaluationService;
import org.example.institutemanagement.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final UnitSelectionRepository unitSelectionRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    @Override
    @Transactional
    public void gradingStudent(EvaluationDto dto) {

        Student student = studentService.findById(dto.studentId());
        Course course = courseService.findById(dto.courseId());

        UnitSelection unitSelection = unitSelectionRepository
                .findByStudentAndCourse(student, course).orElseThrow(
                        () -> new NotFoundException(
                                "student with this course not found")
                );

        unitSelection.setScore(dto.score());
        unitSelectionRepository.save(unitSelection);

    }
}
