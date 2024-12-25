package org.example.institutemanagement.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record EvaluationDto(

        @NotNull(message = "unitSelectionId cannot be null")
        Long unitSelectionId,

        @NotNull(message = "score cannot be null")
        @DecimalMin(value = "0.0", message = "The score must be at least {value}.")
        @DecimalMax(value = "20.0", message = "The score must be at most {value}.")
        Double score
) {
}
