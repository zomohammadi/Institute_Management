package org.example.institutemanagement.dto;

import lombok.Builder;

@Builder
public record ResponseUnitSelectionDto(
        Long unitSelectionID,
        Double score,
        Long studentId,
        Long courseID
) {
}
