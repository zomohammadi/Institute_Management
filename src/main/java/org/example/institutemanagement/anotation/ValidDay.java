package org.example.institutemanagement.anotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.institutemanagement.validation.DayValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = DayValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDay {
    String message() default """
            Invalid day! It must be one of: Monday, Tuesday, Wednesday,
             Thursday, Friday, Saturday, Sunday.
            """;
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

