package org.example.institutemanagement.dto;

import lombok.Builder;

@Builder
public record ResponseCourseDto(

        Long courseId,
        String lessonName,
        String day,
        Integer startHour,
        Integer endHour


) {
}
