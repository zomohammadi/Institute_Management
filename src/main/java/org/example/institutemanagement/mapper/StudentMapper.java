package org.example.institutemanagement.mapper;

import org.example.institutemanagement.dto.ResponseStudentDto;
import org.example.institutemanagement.dto.projection.UnitSelectionProjection;

import java.util.List;

public class StudentMapper {
    public static List<ResponseStudentDto> convertUnitSelectionProjectionToDto(List<UnitSelectionProjection> proj) {
        return proj.stream().map(p -> ResponseStudentDto.builder()
                .score(p.getScore()).id(p.getId()).code(p.getCode()).lastName(p.getLastName())
                .firstName(p.getFirstName())
                .build()).toList();
    }
}
