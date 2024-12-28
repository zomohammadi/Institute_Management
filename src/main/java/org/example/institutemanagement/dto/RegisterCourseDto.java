package org.example.institutemanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.example.institutemanagement.anotation.ValidDay;

@Builder
public record RegisterCourseDto(

        @NotNull(message = "capacity cannot be null")
        @Min(value = 10, message = "capacity must be at least {value}")
        @Max(value = 30, message = "capacity must be at most {value}")
        Integer capacity,

        @NotNull(message = "startHour cannot be null")
        @Min(value = 8, message = "startHour must be at least {value}")
        @Max(value = 18, message = "startHour must be at most {value}")
        Integer startHour,

        @NotNull(message = "endHour cannot be null")
        @Min(value = 9, message = "endHour must be at least {value}")
        @Max(value = 20, message = "endHour must be at most {value}")
        Integer endHour,

        @ValidDay
        String day,

        @NotNull(message = "teacherId cannot be null")
        Long teacherId,

        @NotNull(message = "termId cannot be null")
        Long termId,

        @NotNull(message = "lessonId cannot be null")
        Long lessonId

) {
}
