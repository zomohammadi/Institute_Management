package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterTeacherDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.Teacher;
import org.example.institutemanagement.entity.User;
import org.example.institutemanagement.exception.FoundException;
import org.example.institutemanagement.mapper.PersonMapper;
import org.example.institutemanagement.mapper.TeacherMapper;
import org.example.institutemanagement.repository.TeacherRepository;
import org.example.institutemanagement.service.PersonService;
import org.example.institutemanagement.service.TeacherService;
import org.example.institutemanagement.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;
    private final PersonService personService;
    private final TeacherCodeGenerator teacherCodeGenerator;

    @Override
    @Transactional
    public ResponsePersonDto save(RegisterTeacherDto dto) {

        Person person = personService.findOrCreatePerson(dto);

        if (teacherRepository.existsByPersonId(person.getId())) {
            throw new FoundException("teacher with NationalCode already exists");
        }

        if (userService.existsByUsername(dto.username()))
            throw new FoundException("Username already exists");

        String code = teacherCodeGenerator.generateCode();

        User user = userService.createUser(person, dto.username());

        Teacher teacher = TeacherMapper.createTeacher(code,dto.salary(),person);

        userService.save(user);
        teacherRepository.save(teacher);

        return PersonMapper.createResponsePersonDto(code, person, user);
    }



}

