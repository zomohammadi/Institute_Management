package org.example.institutemanagement.validation;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterTermDto;
import org.example.institutemanagement.exception.FoundException;
import org.example.institutemanagement.repository.TermRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TermValidator {
    private final TermRepository termRepository;

    public void termValidateData(RegisterTermDto dto){
        if (termRepository.existsByTermNumber(dto.termNumber()))
            throw new FoundException("term with this number already exists");

        if (dto.startDate().isAfter(dto.endDate()))
            throw new RuntimeException("start date cannot be after end date");
    }
}
