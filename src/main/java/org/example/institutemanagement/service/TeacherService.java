package org.example.institutemanagement.service;

import org.example.institutemanagement.dto.RegisterTeacherDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherService {

    ResponsePersonDto save(RegisterTeacherDto dto);
}
