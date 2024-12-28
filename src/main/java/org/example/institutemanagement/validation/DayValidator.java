package org.example.institutemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.institutemanagement.anotation.ValidDay;
import org.example.institutemanagement.enumaration.Day;


public class DayValidator implements ConstraintValidator<ValidDay,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(value==null){
             return false;
        }

        for (Day day : Day.values()) {
            if(day.name().equalsIgnoreCase(value))
                return true;
        }
        return false;
    }
}
