package org.example.institutemanagement.repository;

import org.example.institutemanagement.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    boolean existsByTitle(String title);
}
