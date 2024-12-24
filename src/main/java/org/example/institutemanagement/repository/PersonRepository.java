package org.example.institutemanagement.repository;

import org.example.institutemanagement.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
   // boolean existsByNationalCode(String nationalCode);
    Optional<Person> findByNationalCode(String nationalCode);
}
