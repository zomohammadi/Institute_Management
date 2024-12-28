package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.RegisterTermDto;
import org.example.institutemanagement.entity.Term;
import org.example.institutemanagement.mapper.TermMapper;
import org.example.institutemanagement.repository.TermRepository;
import org.example.institutemanagement.service.TermService;
import org.example.institutemanagement.validation.TermValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermServiceImpl implements TermService {

    private final TermRepository termRepository;
    private final TermValidator termValidator;

    @Override
    @Transactional
    public void save(RegisterTermDto dto) {

        termValidator.termValidateData(dto);

        Term term = TermMapper.createTerm(dto);

        termRepository.save(term);
    }
}
