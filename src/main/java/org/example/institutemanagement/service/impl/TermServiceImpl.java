package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterTermDto;
import org.example.institutemanagement.entity.Term;
import org.example.institutemanagement.exception.FoundException;
import org.example.institutemanagement.repository.TermRepository;
import org.example.institutemanagement.service.TermService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermServiceImpl implements TermService {

    private final TermRepository termRepository;

    @Override
    @Transactional
    public void save(RegisterTermDto dto) {

        if (termRepository.existsByTermNumber(dto.termNumber()))
            throw new FoundException("term with this number already exists");

        if (dto.startDate().isAfter(dto.endDate()))
            throw new RuntimeException("start date cannot be after end date");

        Term term = Term.builder().termNumber(dto.termNumber())
                .startDate(dto.startDate()).endDate(dto.endDate()).build();

        termRepository.save(term);
    }
}
