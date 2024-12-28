package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.dto.PersonDto;
import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.mapper.PersonMapper;
import org.example.institutemanagement.repository.PersonRepository;
import org.example.institutemanagement.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public <T extends PersonDto> Person findOrCreatePerson(T dto) {
        return personRepository.findByNationalCode(dto.nationalCode())
                .orElseGet(() -> {
                    Person newPerson = PersonMapper.createPerson(dto);

                    return personRepository.save(newPerson);
                });
    }

}
