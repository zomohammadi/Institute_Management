package org.example.institutemanagement.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RequestCourseDto(
        @NotNull(message = "teacherId cannot be Null")
        @Min(value = 1, message = "teacherId must be greater than or equal to {value}")
        @Max(value = Long.MAX_VALUE, message = "teacherId must be less than or equal to {value}")

        Long teacherId,
        @NotNull(message = "termId cannot be Null")
        @Min(value = 1, message = "termId must be greater than or equal to {value}")
        @Max(value = Long.MAX_VALUE, message = "termId must be less than or equal to {value}")

        Long termId
) {
}
