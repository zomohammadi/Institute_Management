package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterStudentDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.Student;
import org.example.institutemanagement.entity.User;
import org.example.institutemanagement.exception.FoundException;
import org.example.institutemanagement.mapper.PersonMapper;
import org.example.institutemanagement.mapper.StudentMapper;
import org.example.institutemanagement.repository.StudentRepository;
import org.example.institutemanagement.service.PersonService;
import org.example.institutemanagement.service.StudentService;
import org.example.institutemanagement.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentCodeGenerator studentCodeGenerator;
    private final PersonService personService;
    private final UserService userService;

    @Override
    @Transactional
    public ResponsePersonDto save(RegisterStudentDto dto) {

        Person person = personService.findOrCreatePerson(dto);

        if (studentRepository.existsByPersonId(person.getId())) {
            throw new FoundException("Student with NationalCode already exists");
        }
        if (userService.existsByUsername(dto.username()))
            throw new FoundException("Username already exists");

        String code = studentCodeGenerator.generateCode(Integer.valueOf(dto.enteringYear()));

        User user = userService.createUser(person, dto.username());

        Student student = StudentMapper.createStudent(code,dto.enteringYear(),person);


        userService.save(user);
        studentRepository.save(student);

        return PersonMapper.createResponsePersonDto(code, user, person);
    }

    @Override
    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new FoundException("Student not found"));
    }


}

        /*Person person = personRepository.findByNationalCode(dto.nationalCode());

        if (person == null) {

            person = Person.builder().firstName(dto.firstName()).lastName(dto.lastName())
                    .mobileNumber(dto.mobileNumber()).nationalCode(dto.nationalCode()).emailAddress(dto.emailAddress())
                    .build();
            personRepository.save(person);

        } else if (studentRepository.existsByPersonId(person.getId()))
            throw new FoundException("Student with NationalCode already exists");

        String code = dto.enteringYear().toString().concat(GenerateCode.generateCode());

        User user = User.builder().person(person).username(dto.username())
                .password(GenerateCode.generateSecurePassword()).build();

        Student student = Student.builder().code(code).enteringYear(dto.enteringYear()).person(person).build();

        userRepository.save(user);
        studentRepository.save(student);

        return ResponsePersonDto.builder().code(code).password(user.getPassword()).build();
    }*/