package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.ResponseStudentDto;
import org.example.institutemanagement.dto.projection.UnitSelectionProjection;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.entity.Student;
import org.example.institutemanagement.entity.UnitSelection;
import org.example.institutemanagement.exception.FoundException;
import org.example.institutemanagement.mapper.StudentMapper;
import org.example.institutemanagement.repository.CourseRepository;
import org.example.institutemanagement.repository.UnitSelectionRepository;
import org.example.institutemanagement.service.CourseService;
import org.example.institutemanagement.service.StudentService;
import org.example.institutemanagement.service.UnitSelectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UnitSelectionServiceImpl implements UnitSelectionService {

    private final UnitSelectionRepository unitSelectionRepository;
    private final CourseService courseService;
    private final CourseRepository courseRepository;
    private final StudentService studentService;

    @Override
    @Transactional
    public void selectUnit(Long studentId, Long courseId) {

        Course course = courseService.findById(courseId);
        Student student = studentService.findById(studentId);

        if (course.getCapacity() <= 0)
            throw new RuntimeException("Capacity is zero");

        if (unitSelectionRepository.isSelectCourseInThisTime(
                course.getStartHour(),course.getEndHour(),
                course.getTerm(),course.getDay(), student))
            throw new FoundException("conflict in selected unit");

        course.setCapacity(course.getCapacity() - 1);

        courseRepository.save(course);
        unitSelectionRepository.save(UnitSelection.builder()
                .student(student).course(course).build());

    }

    @Override
    @Transactional
    public void save(UnitSelection unitSelection){
        unitSelectionRepository.save(unitSelection);
    }

    @Override
    public UnitSelection findById(Long unitSelectionId){
        return unitSelectionRepository.findById(unitSelectionId).orElseThrow(
                () -> new FoundException("unit selection not found")
        );
    }

    @Override
    public List<ResponseStudentDto> findStudentsWithScore(Long courseId){

        List<UnitSelectionProjection> proj = unitSelectionRepository.findStudentsWithScore(courseId);

        return StudentMapper.convertUnitSelectionProjectionToDto(proj);

    }
}
