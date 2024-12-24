package org.example.institutemanagement.dto;

import lombok.Builder;

@Builder
public record FieldErrorDto(
        String field,
        String message
) {
}
