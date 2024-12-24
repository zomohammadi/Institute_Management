package org.example.institutemanagement.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisterTermDto(

        @NotNull(message = "Term Number cannot be Null")
        @Min(value = 1, message = "Term Number must be greater than or equal to {value}")
        @Max(value = Long.MAX_VALUE, message = "Term Number must be less than or equal to {value}")
        Long termNumber,

        @NotNull(message = "startDate cannot be Null")
        @Future(message = "startDate must be in the future")
        LocalDate startDate,

        @NotNull(message = "endDate cannot be Null")
        @Future(message = "endDate must be in the future")
        LocalDate endDate

) {
}
