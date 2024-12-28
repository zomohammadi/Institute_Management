package org.example.institutemanagement.mapper;

import org.example.institutemanagement.dto.PersonDto;
import org.example.institutemanagement.dto.ResponsePersonDto;
import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.User;

public class PersonMapper {
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
    public static <T extends PersonDto> Person createPerson(T dto){
        return Person.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .mobileNumber(dto.mobileNumber())
                .nationalCode(dto.nationalCode())
                .emailAddress(dto.emailAddress())
                .build();
    }
}
