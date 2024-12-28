package org.example.institutemanagement.service;

import org.example.institutemanagement.dto.RegisterTeacherDto;
import org.example.institutemanagement.entity.Person;

public interface PersonService {
    Person findOrCreatePerson(RegisterTeacherDto dto);
}
