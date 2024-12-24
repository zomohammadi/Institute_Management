package org.example.institutemanagement.repository;

import org.example.institutemanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByPersonId(Long personId);
}
