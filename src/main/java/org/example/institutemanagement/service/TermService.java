package org.example.institutemanagement.service;

import org.example.institutemanagement.dto.RegisterTermDto;
import org.springframework.transaction.annotation.Transactional;

public interface TermService {

    @Transactional
    void save(RegisterTermDto term);
}