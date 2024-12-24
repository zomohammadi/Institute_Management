package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterStudentDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.Student;
import org.example.institutemanagement.entity.User;
import org.example.institutemanagement.exception.FoundException;
import org.example.institutemanagement.repository.PersonRepository;
import org.example.institutemanagement.repository.StudentRepository;
import org.example.institutemanagement.repository.UserRepository;
import org.example.institutemanagement.service.StudentService;
import org.example.institutemanagement.util.GenerateCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponsePersonDto save(RegisterStudentDto dto) {

        Person person = findOrCreatePerson(dto);

        if (studentRepository.existsByPersonId(person.getId())) {
            throw new FoundException("Student with NationalCode already exists");
        }

        String code = generateStudentCode(Integer.valueOf(dto.enteringYear()));
        User user = createUser(person, dto.username());
        Student student = createStudent(person, Integer.valueOf(dto.enteringYear()), code);

        userRepository.save(user);
        studentRepository.save(student);

        return ResponsePersonDto.builder()
                .code(code)
                .password(user.getPassword())
                .build();
    }



    private Person findOrCreatePerson(RegisterStudentDto dto) {
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

    private String generateStudentCode(Integer enteringYear) {
        return enteringYear.toString().concat(GenerateCode.generateCode());
    }

    private User createUser(Person person, String username) {
        return User.builder()
                .person(person)
                .username(username)
                .password(GenerateCode.generateSecurePassword())
                .build();
    }

    private Student createStudent(Person person, Integer enteringYear, String code) {
        return Student.builder()
                .code(code)
                .enteringYear(enteringYear)
                .person(person)
                .build();
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