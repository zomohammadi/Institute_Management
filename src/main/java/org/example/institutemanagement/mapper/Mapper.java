package org.example.institutemanagement.mapper;

import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.dto.ResponseStudentDto;
import org.example.institutemanagement.dto.projection.UnitSelectionProjection;
import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.User;

import java.util.List;

public class Mapper {
    public static ResponsePersonDto createResponsePersonDto(String code, User user, Person person) {
        return ResponsePersonDto.builder().code(code).password(user.getPassword()).firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmailAddress())
                .username(user.getUsername())
                .build();
    }

    public static ResponsePersonDto createResponsePersonDto(String code, Person person, User user) {
        return ResponsePersonDto.builder().code(code).password(user.getPassword())
                .firstName(person.getFirstName()).lastName(person.getLastName())
                .email(person.getEmailAddress()).username(user.getUsername()).build();
    }

    public static List<ResponseStudentDto> convertUnitSelectionProjectionToResponseStudentDtoList(List<UnitSelectionProjection> proj){
        return proj.stream().map(p->ResponseStudentDto.builder()
                .score(p.getScore()).id(p.getId()).code(p.getCode()).lastName(p.getLastName())
                .firstName(p.getFirstName())
                .build()).toList();
    }
}
