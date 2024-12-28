package org.example.institutemanagement.mapper;

import org.example.institutemanagement.dto.RegisterTermDto;
import org.example.institutemanagement.entity.Term;

public class TermMapper {


    public static Term createTerm(RegisterTermDto dto) {
        return   Term.builder().termNumber(dto.termNumber())
                .startDate(dto.startDate()).endDate(dto.endDate()).build();
    }
}
