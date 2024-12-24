package org.example.institutemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterLessonDto(

        @NotBlank(message = "title cannot be Blank")
        @Size(min = 3, max = 30, message = "title must be less than {max} characters" +
                "and greater Than {min} characters")
        @Pattern(regexp = "^[a-zA-Z0-9_.-]*$", message = "title can only contain letters and numbers")
        String title,

        @NotBlank(message = "unit  cannot be Blank")
        @Pattern(regexp = "\\d", message = "unit must be 1 number of digit")
        String unit

) {
}
