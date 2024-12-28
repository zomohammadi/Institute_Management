package org.example.institutemanagement.mapper;

import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.Teacher;

public class TeacherMapper {
    public static Teacher createTeacher(String code, Double salary, Person person) {
        return Teacher.builder()
                .code(code)
                .salary(salary)
                .person(person)
                .build();
    }
}
