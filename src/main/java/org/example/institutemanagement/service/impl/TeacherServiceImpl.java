package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterTeacherDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.Teacher;
import org.example.institutemanagement.entity.User;
import org.example.institutemanagement.exception.FoundException;
import org.example.institutemanagement.repository.PersonRepository;
import org.example.institutemanagement.repository.TeacherRepository;
import org.example.institutemanagement.repository.UserRepository;
import org.example.institutemanagement.service.TeacherService;
import org.example.institutemanagement.util.GenerateCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public ResponsePersonDto save(RegisterTeacherDto dto) {

        Person person = findOrCreatePerson(dto);

        if (teacherRepository.existsByPersonId(person.getId())) {
            throw new FoundException("teacher with NationalCode already exists");
        }
        if (userRepository.existsByUsername(dto.username()))
            throw new FoundException("Username already exists");

        String code = generateTeacherCode();
        User user = createUser(person, dto.username());
       Teacher teacher = createStudent(person, dto.salary(), code);

        userRepository.save(user);
        teacherRepository.save(teacher);

        return ResponsePersonDto.builder()
                .code(code)
                .password(user.getPassword())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmailAddress())
                .username(user.getUsername())
                .build();
    }


    private Person findOrCreatePerson(RegisterTeacherDto dto) {
        return personRepository.findByNationalCode(dto.nationalCode())
                .orElseGet(() -> {
                    Person newPerson = Person.builder()
                            .firstName(dto.firstName())
                            .lastName(dto.lastName())
                            .mobileNumber(dto.mobileNumber())
                            .nationalCode(dto.nationalCode())
                            .emailAddress(dto.emailAddress())
                            .build();
                    return personRepository.save(newPerson);
                });
    }

    private String generateTeacherCode() {
        return GenerateCode.generateCode().concat(GenerateCode.generateCode());
    }

    private User createUser(Person person, String username) {
        return User.builder()
                .person(person)
                .username(username)
                .password(GenerateCode.generateSecurePassword())
                .build();
    }

    private Teacher createStudent(Person person, Double salary, String code) {
        return Teacher.builder()
                .code(code)
                .salary(salary)
                .person(person)
                .build();
    }


}
