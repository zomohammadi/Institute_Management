package org.example.institutemanagement.service;


import org.example.institutemanagement.dto.RegisterStudentDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.entity.Student;

public interface StudentService {

    ResponsePersonDto save(RegisterStudentDto dto);

    Student findById(Long studentId);
}
