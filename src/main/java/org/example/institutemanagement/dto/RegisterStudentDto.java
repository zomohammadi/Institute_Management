package org.example.institutemanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record RegisterStudentDto(
        @NotBlank(message = "FirstName cannot be Blank")
        @Size(min = 3, max = 30, message = "FirstName must be less than {max} characters" +
                "and greater Than {min} characters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "FirstName can only contain letters")
        String firstName,

        @NotBlank(message = "lastName cannot be Blank")
        @Size(min = 3, max = 30, message = "lastName must be less than {max} characters" +
                "and greater Than {min} characters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "lastName can only contain letters")
        String lastName,


        @NotBlank(message = "National Code cannot be Blank")
        @Pattern(regexp = "\\d{10}", message = "national code must be 10 number of digit")
        String nationalCode,

        @NotBlank(message = "mobileNumber cannot be Blank")
        @Pattern(regexp = "\\d{11}", message = "mobileNumber must be 11 number of digit")
        String mobileNumber,

        @NotBlank(message = "EmailAddress cannot be Blank")
        @Email(message = "EmailAddress must be a valid email address")
        String emailAddress,

        @NotNull(message = "Entering Year cannot be Blank")
        @Pattern(regexp = "\\d{4}", message = "Entering Year must be exactly 4 digits")
        String enteringYear,

        @NotBlank(message = "UserName cannot be Blank")
        @Size(min = 4, max = 20, message = "UserName must be less than {max} characters" +
                "and greater Than {min} characters")
        String username


) implements PersonDto{
}
