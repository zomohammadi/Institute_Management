package org.example.institutemanagement.mapper;

import org.example.institutemanagement.dto.RegisterCourseDto;
import org.example.institutemanagement.dto.ResponseCourseDto;
import org.example.institutemanagement.dto.projection.ResponseCourseProjection;
import org.example.institutemanagement.entity.Course;
import org.example.institutemanagement.entity.Lesson;
import org.example.institutemanagement.entity.Teacher;
import org.example.institutemanagement.entity.Term;
import org.example.institutemanagement.enumaration.Day;

import java.util.List;

public class CourseMapper {
    public static List<ResponseCourseDto> convertCourseProjectionListToDtoList(List<ResponseCourseProjection> proj) {

        return proj.stream().map(proj1 -> ResponseCourseDto.builder()
                .courseId(proj1.getCourseId()).lessonName(proj1.getLessonName())
                .day(proj1.getDay()).startHour(proj1.getStartHour())
                .endHour(proj1.getEndHour()).build()).toList();
    }

    public static Course convertDtoToCourse(RegisterCourseDto dto) {
        return Course.builder().capacity(dto.capacity()).startHour(dto.startHour())
                .endHour(dto.endHour())
                .day(Day.valueOf(dto.day()))
                .teacher(Teacher.builder().id(dto.teacherId()).build())
                .term(Term.builder().id(dto.termId()).build())
                .lesson(Lesson.builder().id(dto.lessonId()).build())
                .build();
    }
}
