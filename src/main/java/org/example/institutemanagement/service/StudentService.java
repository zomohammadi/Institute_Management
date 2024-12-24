package org.example.institutemanagement.service;


import org.example.institutemanagement.dto.RegisterStudentDto;
import org.example.institutemanagement.dto.ResponsePersonDto;

public interface StudentService {

    ResponsePersonDto save(RegisterStudentDto dto);
}
