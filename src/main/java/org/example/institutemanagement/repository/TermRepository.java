package org.example.institutemanagement.repository;

import org.example.institutemanagement.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {

    boolean existsByTermNumber(Long termNumber);
}
