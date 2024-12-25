package org.example.institutemanagement.dto;

import lombok.Builder;

@Builder
public record ResponseStudentDto(
        Long id,
        Double score,
        String firstName,
        String lastName,
        String code
        ) {
}
