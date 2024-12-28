package org.example.institutemanagement.service;

import org.example.institutemanagement.dto.PersonDto;
import org.example.institutemanagement.entity.Person;

public interface PersonService {
    <T extends PersonDto> Person findOrCreatePerson(T dto);
}
