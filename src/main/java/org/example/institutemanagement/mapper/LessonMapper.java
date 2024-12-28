package org.example.institutemanagement.mapper;

import org.example.institutemanagement.dto.RegisterLessonDto;
import org.example.institutemanagement.entity.Lesson;

public class LessonMapper {

    public static Lesson createLesson(RegisterLessonDto dto) {
        return Lesson.builder().title(dto.title()).unit(Integer.valueOf(dto.unit()))
                .build();
    }
}
