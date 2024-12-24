package org.example.institutemanagement.dto;

import lombok.Builder;

@Builder
public record ResponsePersonDto(
        String firstName,
        String lastName,
        String code,
        String email,
        String username,
        String password
) {
}
