package org.example.institutemanagement.service;

import org.example.institutemanagement.dto.RegisterTeacherDto;
import org.example.institutemanagement.dto.ResponsePersonDto;

public interface TeacherService {

    ResponsePersonDto save(RegisterTeacherDto dto);
}
