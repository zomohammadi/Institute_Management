package org.example.institutemanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SelectUnitDto(
        @NotNull(message = "studentId cannot be null") Long studentId,
        @NotNull(message = "courseId cannot be null") Long courseId
) {
}
