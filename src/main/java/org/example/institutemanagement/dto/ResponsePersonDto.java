package org.example.institutemanagement.dto;

import lombok.Builder;

@Builder
public record ResponsePersonDto(
        String code,
        String password
) {
}
