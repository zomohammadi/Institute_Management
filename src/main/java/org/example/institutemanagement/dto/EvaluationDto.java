package org.example.institutemanagement.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record EvaluationDto(

        @NotNull(message = "studentId cannot be null")
        Long studentId,

        @NotNull(message = "courseId cannot be null")
        Long courseId,

        @NotNull(message = "score cannot be null")
        @DecimalMin(value = "0.0", message = "The score must be at least {value}.")
        @DecimalMax(value = "20.0", message = "The score must be at most {value}.")
        Double score
) {
}
