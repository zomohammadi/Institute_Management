package org.example.institutemanagement.repository;

import org.example.institutemanagement.dto.ResponseCourseDto;
import org.example.institutemanagement.dto.projection.ResponseCourseProjection;
import org.example.institutemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("""
            Select c.id as courseId, l.title as lessonName, c.day as day
            , c.startHour as startHour , c.endHour as endHour
            from Course c
            join c.term t
            join c.lesson l where c.teacher.id = :teacherId
            and t.id = :termId
            """
    )
    List<ResponseCourseProjection> findCourses(Long teacherId, Long termId);
}
